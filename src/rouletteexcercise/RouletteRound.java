/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import rouletteexcercise.RouletteGame.BetType;

/**
 *
 * @author Shay
 */
public class RouletteRound {

    private RouletteGame _game;
    private HashMap<String, ArrayList<RouletteBet>> _bets;

    public RouletteRound(RouletteGame game) {
        _game = game;
        _bets = new HashMap<String, ArrayList<RouletteBet>>();
    }
    
    void SetBankruppedPlayersAsNotPlaying() {
        for (Entry<String, RoulettePlayer> pair : _game.GetPlayers().entrySet()) {
            RoulettePlayer player = pair.getValue();

            if (player.GetMoney() == 0) {
                player.SetIsPlaying(false);
            }
        }
    }
    
    public void EndRound(){
        SetBankruppedPlayersAsNotPlaying();
    }

    public void PlaceBet(RoulettePlayer player, RouletteBet bet) throws RoulettePlayer.NotEnoughtMoneyException {
        String playerName = player.GetName();

        if (_bets.containsKey(playerName) == false) {
            _bets.put(playerName, new ArrayList<RouletteBet>());
        }

        player.PayMoney(bet.GetMoney());
        _bets.get(playerName).add(bet);

    }

    public int CalculateWinningForPlayer(String name, RouletteNumber number) {
        int res = 0;

        if (_bets.containsKey(name) == true) {
            ArrayList<RouletteBet> betsPerPlayer = _bets.get(name);
            for (int i = 0; i < betsPerPlayer.size(); i++) {
                res += betsPerPlayer.get(i).CalculateWinning(number);
            }
        }

        return res;
    }

    public RouletteBet GetBetForComputerPlayer(RoulettePlayer player) throws Exception {
        if (player.GetPlayerType() != RoulettePlayer.RoulettePlayerType.COMPUTER)
            throw new Exception("Player is not a computer.");
        
        if (player.GetIsPlaying() == false || player.GetMoney() <= 0)
            throw new Exception("Player is not playing or doesn'y have any money.");
        
        Random rnd = new Random();
        RouletteBet res;

        int index = rnd.nextInt(_game.GetWheel().length); // get a random number for the wheel array
        int money = rnd.nextInt(player.GetMoney() + 1); // set how much money to bet on

        ArrayList<String> numbers = new ArrayList<>();
        numbers.add(_game.GetWheel()[index]); // adds the random number to the bets
        
        try{
            BetType betType = BetType.STRAIGHT;
            res = new RouletteBet(this._game, player, betType, numbers, money);
        }
        catch(ExceptionInInitializerError e)
        {
            throw e;
        }
        
        return res;
    }

    void PlaceComputerizedBets() throws Exception {
        for (Entry<String, RoulettePlayer> entry : _game.GetPlayers().entrySet()){
            RoulettePlayer player = entry.getValue();
            if (player.GetPlayerType() == RoulettePlayer.RoulettePlayerType.COMPUTER && player.GetMoney() > 0){
                RouletteBet bet = GetBetForComputerPlayer(player);
                PlaceBet(player, bet);
            }
        }
    }

    int GetNumberOfBetsOfPlayer(RoulettePlayer player) {
        ArrayList<RouletteBet> bets = _bets.get(player.GetName());
        if (bets != null)
            return bets.size();
        else
            return 0;
    }
}
