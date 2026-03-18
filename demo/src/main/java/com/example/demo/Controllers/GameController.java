package com.example.demo.Controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.example.demo.Model.Game;
import com.example.demo.Model.GameState;
import com.example.demo.Model.JoinRequest;
import com.example.demo.Model.Move;
import com.example.demo.Services.GameService;

public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //Join
    @MessageMapping("/join/{roomId}")
    @SendTo("/topic/game/{roomId}")
    public GameState join(
            @DestinationVariable String roomId,
            JoinRequest request) {

        Game game = gameService.getGame(roomId);

        String result = game.addPlayer(request.playerName);

        return new GameState(game.board, game.currentTurn, result, game.winner);
    }

    //Move
    @MessageMapping("/move/{roomId}")
    @SendTo("/topic/game/{roomId}")
    public GameState move(
            @DestinationVariable String roomId,
            Move move) {

        Game game = gameService.getGame(roomId);

        String msg = game.makeMove(move.row, move.col, move.player);

        return new GameState(game.board, game.currentTurn, msg, game.winner);
    }

    //Leave
    @MessageMapping("/leave/{roomId}")
    @SendTo("/topic/game/{roomId}")
    public GameState leave(@DestinationVariable String roomId) {

        gameService.removePlayer(roomId);

        return new GameState(null, 'X', "Player left. Room closed.", null);
    }

}
