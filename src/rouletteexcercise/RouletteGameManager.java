/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouletteexcercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import rouletteexcercise.RouletteGame.RouletteType;

/**
 *
 * @author Shay
 */
public class RouletteGameManager {

    public void Start() {
        String action;
        boolean keepRunning = true;

        Scanner scanner = new Scanner(System.in);

        while (keepRunning == true) {
            System.out.print("Create a new game (y/n)? ");
            action = (scanner.nextLine().equalsIgnoreCase("y")) ? "NEW_GAME" : "EXIT";
            switch (action) {
                case "NEW_GAME":
                    NewGame();
                    break;
                case "EXIT":
                    keepRunning = false;
                    break;
                default:
                    keepRunning = false;
                    break;
            }
        }
    }

    private RouletteSettings ReadSettings() throws Exception {
        Scanner scanner = new Scanner(System.in);
        RouletteType type;
        int minimumBetPerPlayer;
        int maximumBetsPerPlater;
        int initialMoney;
        int computerizedPlayer, humanPlayers;

        // roulette type
        while (true) {
            System.out.print("Roulette type - French (1), American (2)? ");
            int typeInt = Integer.parseInt(scanner.nextLine());
            if (typeInt != 1 && typeInt != 2) {
                System.out.println("Invalid selection!");
                continue;
            }

            if (typeInt == 1) {
                type = RouletteType.FRENCH;
            } else {
                type = RouletteType.AMERICAN;
            }

            break;
        }

        while (true) {
            System.out.print("Minimum bets per player (0 or 1)? ");
            int num = Integer.parseInt(scanner.nextLine());
            if (num != 0 && num != 1) {
                System.out.println("Invalid selection!");
                continue;
            }

            minimumBetPerPlayer = num;
            break;
        }

        while (true) {
            System.out.print("Maximum bets per player (1 to 10)? ");
            int num = Integer.parseInt(scanner.nextLine());
            if (num < 1 || num > 10) {
                System.out.println("Invalid selection!");
                continue;
            }

            maximumBetsPerPlater = num;
            break;
        }

        while (true) {
            System.out.print("Initial amount of money per player (10 to 100)? ");
            int num = Integer.parseInt(scanner.nextLine());
            if (num < 10 || num > 100) {
                System.out.println("Invalid selection!");
                continue;
            }

            initialMoney = num;
            break;
        }

        while (true) {
            while (true) {
                System.out.print("Number of computerized players (0 to 6)? ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num < 0 || num > 6) {
                    System.out.println("Invalid selection!");
                    continue;
                }

                computerizedPlayer = num;
                break;
            }

            while (true) {
                System.out.print("Number of human players (0 to 6)? ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num < 0 || num > 6) {
                    System.out.println("Invalid selection!");
                    continue;
                }

                humanPlayers = num;
                break;
            }

            if (humanPlayers + computerizedPlayer > 6) {
                System.out.println("Computerized players and human players are bigger than 6.");
                continue;
            } else {
                break;
            }
        }

        System.out.print("Game Name? ");
        String name = scanner.nextLine();

        RouletteSettings res;

        try {
            res = new RouletteSettings(type, minimumBetPerPlayer, maximumBetsPerPlater, initialMoney, computerizedPlayer, humanPlayers, name);

        } catch (Exception ex) {
            System.out.println("Invalid game settings: " + ex.getMessage());
            return ReadSettings();
        }

        return res;
    }

    private void ReadPlayers(RouletteGame game) {
        Scanner s = new Scanner(System.in);

        for (int i = 0; i < game.GetSettings().GetNumOfRealPlayers(); i++) {
            System.out.print("Please enter Player " + (i + 1) + " name: ");
            try {
                game.AddPlayer(RoulettePlayer.RoulettePlayerType.HUMAN, s.nextLine());
            } catch (RoulettePlayer.PlayerNameAlreadyTakenException ex) {
                System.out.println(ex.getMessage());
                i--;
            }
        }

    }

    private void NewGame() {
        System.out.println("\n======== NEW GAME ========");

        boolean keepRunning = true;

        RouletteSettings settings;

        try {
            settings = ReadSettings();
        } catch (Exception e) {
            System.out.println("Failed to read game settings." + e.getMessage());
            return;
        }

        RouletteGame game = new RouletteGame(settings);
        ReadPlayers(game);
        game.CreateComputerizedPlayers();
        
        while (game.GetActivePlayersNumbers() > 0 && keepRunning == true) {
//        while (game.GetActiveHumanPlayersNumber() > 0 && keepRunning == true) {
            game.NewRound();
            System.out.println("\n******* NEW ROUND *******");

            // place bets
            for (Entry<String, RoulettePlayer> entry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = entry.getValue();
                if (PlaceBets(game, game.GetRound(), player) == false) {
                    if (game.GetRound().GetNumberOfBetsOfPlayer(player) < game.GetSettings().GetMinimumBetsPerPlayer())
                    {
                        System.out.println("You have not reached the minimum bets, you are out.");
                        player.SetIsPlaying(false);
                       
                    }
                    else
                    {
                        System.out.println("Seems like you don't want to bet anymore, would you like to exit (y/n)? ");
                        Scanner scanner = new Scanner(System.in);
                        String str = scanner.nextLine();

                        if (str.equals("y")) {
                            keepRunning = false;
                        } 
                    }
                }
            }

            // get number from wheel
            RouletteNumber number = game.TurnWheel();
            System.out.println("\nTurning wheel...... \nThe roulette number won is: " + number.GetName());
            System.out.println("==================================");
            // get winning money
            for (Entry<String, RoulettePlayer> playerEntry : game.GetPlayers().entrySet()) {
                RoulettePlayer player = playerEntry.getValue();

                int winningMoney = game.GetRound().CalculateWinningForPlayer(player.GetName(), number);

                if (winningMoney > 0) {
                    System.out.println(player.GetName() + " won " + winningMoney);
                    player.RecieveMoney(winningMoney);
                } else {
                    System.out.println(player.GetName() + " didn't win nothing. ");
                }
            }

            game.EndRound();
        }

        System.out.println("\nNo more active human players left.");
        ShowScoreBoard(game);
    }

    private void ShowScoreBoard(RouletteGame game) {
        System.out.println("\n//////// SCORE BOARD ////////");
        RoulettePlayer[] players = game.GetPlayers().values().toArray(new RoulettePlayer[game.GetPlayers().size()]);
        SortPlayersByMoney(players);
        for (int i = 0; i < players.length; i++) {
            RoulettePlayer player = players[i];
            System.out.println(player.GetName() + ": " + player.GetMoney());
        }

        System.out.println("");
    }

    private boolean PlaceBets(RouletteGame game, RouletteRound round, RoulettePlayer player) {

        if (player.GetMoney() <= 0) {
            System.out.println(player.GetName() + ", you don't have any more money left.");
            return true;
        }
        
        if (round.GetNumberOfBetsOfPlayer(player) == game.GetSettings().GetMaximumBetsPerPlayer()){
            System.out.println(player.GetName() + ", you've reached the maximum bets you can make.");
            return true;
        }

        RouletteBet bet;

        // player is computer
        if (player.IsHuman() == false) {
            try {
                bet = round.GetBetForComputerPlayer(player);
                round.PlaceBet(player, bet);
                System.out.println(bet);
                return true;
            } catch (Exception e) {
                System.out.println("Failed to place bet for computer player (" + player.GetName() + ").");
            }
        }

        // player is human
        boolean keepPlaying = AskPlayerIfHeWantsToKeepPlaying(player);
        if (keepPlaying == false) {
            player.SetIsPlaying(false);
            return false;
        }

        RouletteGame.BetType type = GetBetTypeFromConsole(game.GetSettings().GetRouletteType());
        int money = ReadAmountOfMoneyFromPlayer(player.GetMoney());
        ArrayList<String> numbers = null;

        while (true) {
            if (type.NeedsNumbers == true) {
                numbers = ReadNumbersFromConsole(game, type);
            }

            try {
                bet = new RouletteBet(game, player, type, numbers, money);
                break;
            } catch (Exception ex) {
                System.out.println("Bet invalid! " + ex.getMessage());
            }
        }

        try {
            round.PlaceBet(player, bet);
            System.out.println("\n" + bet);
        } catch (RoulettePlayer.NotEnoughtMoneyException e) {
            return PlaceBets(game, round, player);
        }

        System.out.println("\nPlace another bet (y/n)?");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("y")) {
            return PlaceBets(game, round, player);
        } else {
            return true;
        }
    }

    private RouletteGame.BetType GetBetTypeFromConsole(RouletteGame.RouletteType type) {
        System.out.println("\nChoose bet type: ");

        RouletteGame.BetType[] betTypes = type.BetsTypes;
        for (int i = 0; i < betTypes.length; i++) {
            System.out.print((i + 1) + ". " + betTypes[i].name() + "\t");

            if (i % 5 == 0) {
                System.out.println("");
            }
        }
        System.out.print("\n");

        System.out.print("Your selection: ");
        Scanner scanner = new Scanner(System.in);
        int selection = Integer.parseInt(scanner.nextLine());

        while (true) {
            if (selection >= 1 && selection <= betTypes.length) {
                return betTypes[selection - 1];
            } else {
                return GetBetTypeFromConsole(type);
            }
        }
    }

    private ArrayList<String> ReadNumbersFromConsole(RouletteGame game, RouletteGame.BetType type) {
        System.out.print("\nHow many numbers do you want to give? ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Integer numbersToGet = Integer.parseInt(str);
        ArrayList<String> res = new ArrayList<>();

        if (numbersToGet <= 0) {
            System.out.println("Please give a positive number.");
            return ReadNumbersFromConsole(game, type);
        }

        for (int i = 0; i < numbersToGet; i++) {
            System.out.print("Number " + (i + 1) + " of " + numbersToGet + ": ");
            String num = scanner.nextLine();
            if (game.IsNumberValidForBet(num) == true && !res.contains(num)) {
                res.add(num);
            } else {
                System.out.println("Number invalid!");
                i--;
            }
        }

        return res;
    }

    private int ReadAmountOfMoneyFromPlayer(int max) {
        while (true) {
            System.out.println("\nPlace a bet between 0 to " + max + ": ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            Integer money = Integer.parseInt(str);

            if (money >= 0 && money <= max) {
                return money;
            }
        }
    }

    private boolean AskPlayerIfHeWantsToKeepPlaying(RoulettePlayer player) {
        System.out.print("\n" + player.GetName() + ", would you like to place a bet (y) or game out (n)? ");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equals("y") == true) {
            return true;
        } else {
            return false;
        }
    }

    private void SortPlayersByMoney(RoulettePlayer[] players) {
        int n = players.length;
        RoulettePlayer temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (players[j - 1].GetMoney() < players[j].GetMoney()) {
                    //swap the elements!
                    temp = players[j - 1];
                    players[j - 1] = players[j];
                    players[j] = temp;
                }
            }
        }
    }
}
