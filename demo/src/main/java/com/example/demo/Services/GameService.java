package com.example.demo.Services;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Game;

@Service
public class GameService {
    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    public Game getGame(String roomId) {
        return games.computeIfAbsent(roomId, id -> new Game());
    }

    public void removePlayer(String roomId) {
        games.remove(roomId); 
    }

}
