package fr.ryukk.morpion.game;

import fr.ryukk.morpion.game.listener.KeyboardInteractListener;
import fr.ryukk.morpion.game.listener.MouseInteractListener;
import fr.ryukk.morpion.game.player.AIPlayer;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.game.Tile; // Added for Tile.TileType, though may be implicitly available
import fr.ryukk.morpion.utils.Serie;
import fr.ryukk.morpion.utils.UtilDraw;
import fr.ryukk.morpion.utils.View;

import java.awt.*;
import java.util.Random;

import static fr.ryukk.morpion.utils.Constants.*;

public final class Game extends View {

    private boolean started;

    private Grid grid;

    private Player[] players;
    private byte playerTurn;

    private int winner;

    public Game(Player[] players) {
        started = false;

        grid = new Grid(3, 3);
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                add(grid.get(x, y));

        add(grid);

        this.players = players;

        assert !players[0].getTileType().equals(players[1].getTileType()) &&
                !players[0].getTileType().equals(Tile.TileType.NONE) &&
                !players[1].getTileType().equals(Tile.TileType.NONE) : "Players cannot have the same tile type";

        Random r = new Random();
        playerTurn = (byte) r.nextInt(2);

        winner = -1;

        addMouseListener(new MouseInteractListener());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyboardInteractListener());
    }

    public Game(Player[] players, byte first) {
        this(players);

        playerTurn = first;
    }

    public void start() {
        started = true;

        while(winner == -1) {
            Player currentPlayer = players[playerTurn];
            Player opponentPlayer = players[playerTurn == 0 ? 1 : 0];
            Tile.TileType opponentTileType = opponentPlayer.getTileType();

            if (currentPlayer instanceof AIPlayer) {
                ((AIPlayer) currentPlayer).setGameContext(this.grid, opponentTileType);
            }

            players[playerTurn].turn();
            checkWin();

            playerTurn = playerTurn == 0 ? (byte) 1 : 0;
        }

        endGame();
    }

    public void endGame() {
        if(winner >= 0 && winner < 2)
            players[winner].addVictory();
    }

    private void checkWin() {
        Serie p1 = new Serie(players[0].getTileType());
        Serie p2 = new Serie(players[1].getTileType());

        if(p1.calc(grid, 3) > 0)
            winner = 0;
        else if(p2.calc(grid, 3) > 0)
            winner = 1;
        else {
            boolean finished = true;

            for(int x = 0; x < 3; x++)
                for(int y = 0; y < 3; y++)
                    if(grid.get(x, y).getTileType().equals(Tile.TileType.NONE)) finished = false;

            if(finished) winner = 2;
        }

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        renderBackground(g2d);
        renderConfig(g2d);
    }

    @Override
    public void update() { }

    private void renderBackground(Graphics2D g2d) {
        // Background
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void renderConfig(Graphics2D g2d) {
        int baseX = WINDOW_WIDTH - WINDOW_HEIGHT;
        Player[] players = getPlayers();

        // Title
        g2d.setColor(TITLE_FONT_COLOR);
        g2d.setFont(TITLE_FONT);

        String title = "Morpion";

        g2d.drawString(title, WINDOW_HEIGHT + (baseX / 2) - (g2d.getFontMetrics(TITLE_FONT).stringWidth(title) / 2),
                70);

        // Players shapes, names & types
        for (int i = 0; i < 2; i++) {
            // Vars
            Player p = players[i];

            String name = p.getName();
            String type = p.getPlayerType().getName();

            g2d.setFont(NAME_FONT);
            int nameLength = g2d.getFontMetrics().stringWidth(name);

            g2d.setFont(TYPE_FONT);
            int typeLength = g2d.getFontMetrics().stringWidth(type);

            int playerTotalLength = PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength + PLAYERS_NAME_TYPE_X_OFFSET
                    + typeLength;
            int posX = WINDOW_HEIGHT + (baseX / 2) - (playerTotalLength / 2);
            int relativeYOffset = i * NAME_FONT.getSize() * 2;

            // Names
            g2d.setFont(NAME_FONT);
            g2d.setColor(p.getTileType().equals(Tile.TileType.X) ? X_COLOR : O_COLOR);
            g2d.drawString(name, posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET, PLAYERS_Y_OFFSET
                    + (NAME_FONT.getSize() / 2) - 2 + relativeYOffset);

            // Types
            g2d.setFont(TYPE_FONT);

            int padding = 2;

            g2d.setColor(p.getTileType().equals(Tile.TileType.X) ? X_TYPE_BACKGROUND_COLOR : O_TYPE_BACKGROUND_COLOR);
            g2d.fillRoundRect(posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength
                    + PLAYERS_NAME_TYPE_X_OFFSET - padding, PLAYERS_Y_OFFSET + (NAME_FONT.getSize() / 2) - 2
                    + relativeYOffset - ((NAME_FONT.getSize() - TYPE_FONT.getSize()) / 3) - TYPE_FONT.getSize()
                    - padding, typeLength + 1 + (2 * padding), TYPE_FONT.getSize() + 3 + (2 * padding), 5, 5);

            g2d.setColor(TYPE_FONT_COLOR);
            g2d.drawString(type, posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength
                    + PLAYERS_NAME_TYPE_X_OFFSET, PLAYERS_Y_OFFSET + (NAME_FONT.getSize() / 2) - 2 + relativeYOffset
                    - ((NAME_FONT.getSize() - TYPE_FONT.getSize()) / 3));

            // Shapes
            g2d.setStroke(MINI_LINE_STROKE);

            if (p.getTileType().equals(Tile.TileType.X)) {
                g2d.setColor(X_COLOR);
                UtilDraw.drawCross(g2d, posX, PLAYERS_Y_OFFSET - (PLAYERS_SHAPE_SIZE / 2) + relativeYOffset,
                        PLAYERS_SHAPE_SIZE);
            } else {
                g2d.setColor(O_COLOR);
                UtilDraw.drawCircle(g2d, posX, PLAYERS_Y_OFFSET - (PLAYERS_SHAPE_SIZE / 2) + relativeYOffset,
                        PLAYERS_SHAPE_SIZE);
            }

        }

        // Players scores
        String[] scores = new String[]{String.valueOf(getPlayers()[0].getVictoryCount()),
                String.valueOf(getPlayers()[1].getVictoryCount())};

        String separator = "-";

        g2d.setFont(SCORE_FONT);
        FontMetrics fm = g2d.getFontMetrics();

        int separatorLength = fm.stringWidth(separator);
        int scoreTotalLength = fm.stringWidth(scores[0]) + fm.stringWidth(scores[1]) + separatorLength
                + (2 * SCORES_SEPARATOR_OFFSET);

        g2d.setColor(SEPARATOR_FONT_COLOR);
        g2d.drawString(separator, WINDOW_HEIGHT + (baseX / 2) - (separatorLength / 2), SCORES_Y_OFFSET);

        for (int i = 0; i < 2; i++) {
            g2d.setColor(players[i].getTileType().equals(Tile.TileType.X) ? X_COLOR : O_COLOR);

            int temp = 0;
            if (i - 1 >= 0)
                for (int j = i - 1; j >= 0; j--)
                    temp += fm.stringWidth(scores[j]);

            int relativeXOffset = i * (separatorLength + (SCORES_SEPARATOR_OFFSET * 2)) + temp;

            g2d.drawString(scores[i], WINDOW_HEIGHT + (baseX / 2) - (scoreTotalLength  / 2) + relativeXOffset,
                    SCORES_Y_OFFSET);
        }

        // Player turn & win
        g2d.setStroke(LINE_STROKE);

        if(isStarted())
            if (isFinished()) {
                if(getWinner() == null) {
                    g2d.setFont(WIN_FONT);
                    g2d.setColor(WIN_TIE_FONT_COLOR);

                    String tie = "MATCH NUL";

                    g2d.drawString(tie, WINDOW_HEIGHT + (baseX / 2) - (g2d.getFontMetrics().stringWidth(tie) / 2)
                            , WIN_TIE_Y_OFFSET);
                }
                else {
                    if (getWinner().getTileType().equals(Tile.TileType.X)) {
                        g2d.setColor(X_COLOR);
                        UtilDraw.drawCross(g2d, WINDOW_HEIGHT + (baseX / 2) - (WIN_SHAPE_SIZE / 2), TURN_Y_OFFSET, WIN_SHAPE_SIZE);
                    } else {
                        g2d.setColor(O_COLOR);
                        UtilDraw.drawCircle(g2d, WINDOW_HEIGHT + (baseX / 2) - (WIN_SHAPE_SIZE / 2), TURN_Y_OFFSET, WIN_SHAPE_SIZE);
                    }

                    g2d.setFont(WIN_FONT);
                    g2d.setColor(WIN_TIE_FONT_COLOR);

                    String win = "GAGNE";

                    g2d.drawString(win, WINDOW_HEIGHT + (baseX / 2) - (g2d.getFontMetrics().stringWidth(win) / 2)
                            , WIN_Y_OFFSET);
                }

            }
            else {
                if (getPlayerTurn().getTileType().equals(Tile.TileType.X)) {
                    g2d.setColor(X_COLOR);
                    UtilDraw.drawCross(g2d, WINDOW_HEIGHT + (baseX / 2) - (TURN_SHAPE_SIZE / 2), TURN_Y_OFFSET, TURN_SHAPE_SIZE);
                } else {
                    g2d.setColor(O_COLOR);
                    UtilDraw.drawCircle(g2d, WINDOW_HEIGHT + (baseX / 2) - (TURN_SHAPE_SIZE / 2), TURN_Y_OFFSET, TURN_SHAPE_SIZE);
                }

            }

    }

    public Grid getGrid() { return grid; }

    public Player[] getPlayers() { return players; }
    public Player getPlayerTurn() { return players[playerTurn]; }
    public Player getWinner() { return winner >= 0 && winner < 2 ? players[winner] : null; }
    public Player getPlayerByTileType(Tile.TileType tileType) {
        if(players[0].getTileType().equals(tileType))
            return players[0];
        else
            return players[1];
    }

    public boolean isStarted() { return started; }
    public boolean isFinished() { return winner >= 0; }

}
