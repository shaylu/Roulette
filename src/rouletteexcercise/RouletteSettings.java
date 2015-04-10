/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;
import rouletteexcercise.RouletteGame.RouletteType;

/**
 *
 * @author Shay
 */

public class RouletteSettings {

    private RouletteType _rouletteType;
    private int _minimumBetsPerPlayer;
    private int _maximumBetsPerPlayer;
    private int _initialAmoutOfMoneyPerPlayer;
    private int _numOfComputerizedPlayers;
    private int _numOfRealPlayers;
    private String _gameName;

    public RouletteSettings(RouletteType _rouletteType, int _minimumBetsPerPlayer, int _maximumBetsPerPlayer, int _initialAmoutOfMoneyPerPlayer, int _numOfComputerizedPlayers, int _numOfRealPlayers, String _gameName) throws Exception{
        this._rouletteType = _rouletteType;
        this._minimumBetsPerPlayer = _minimumBetsPerPlayer;
        this._maximumBetsPerPlayer = _maximumBetsPerPlayer;
        this._initialAmoutOfMoneyPerPlayer = _initialAmoutOfMoneyPerPlayer;
        this._numOfComputerizedPlayers = _numOfComputerizedPlayers;
        this._numOfRealPlayers = _numOfRealPlayers;
        this._gameName = _gameName;
        
        Validate();
    }
    
    public RouletteType GetRouletteType()
    {
        return _rouletteType;
    }
    
    public int GetInitialAmoutOfMoneyPerPlayer()
    {
        return _initialAmoutOfMoneyPerPlayer;
    }
    
    /**
     * @return the _minimumBetsPerPlayer
     */
    public int GetMinimumBetsPerPlayer() {
        return _minimumBetsPerPlayer;
    }

    /**
     * @return the _maximumBetsPerPlayer
     */
    public int GetMaximumBetsPerPlayer() {
        return _maximumBetsPerPlayer;
    }

    /**
     * @return the _numOfComputerizedPlayers
     */
    public int GetNumOfComputerizedPlayers() {
        return _numOfComputerizedPlayers;
    }

    /**
     * @return the _numOfRealPlayers
     */
    public int GetNumOfRealPlayers() {
        return _numOfRealPlayers;
    }

    /**
     * @return the _gameName
     */
    public String GetGameName() {
        return _gameName;
    }

    private void Validate() throws Exception {

        if (_minimumBetsPerPlayer != 0 && _minimumBetsPerPlayer != 1)
            throw new Exception("Minimum bets per player must be 0 or 1");
        
        if (_maximumBetsPerPlayer < 1 || _maximumBetsPerPlayer > 10)
            throw new Exception("Maximum bets per player must be between 1 to 10");
        
        if (_initialAmoutOfMoneyPerPlayer < 10 || _initialAmoutOfMoneyPerPlayer > 100)
            throw new Exception("Initial of money per player must be between 10 to 100");
        
        if (_numOfComputerizedPlayers < 0 || _numOfComputerizedPlayers > 6)
            throw new Exception("Computerized players must be between 0 to 6.");
        
        if (_numOfRealPlayers < 0 || _numOfRealPlayers > 6)
            throw new Exception("Human players must be between 0 to 6.");
        
        if (_numOfRealPlayers + _numOfComputerizedPlayers > 6)
            throw new Exception("Number of human players and computerized players is bigger than 6.");
    }
}
