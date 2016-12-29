package fr.ryukk.morpion.utils;

import java.awt.*;

public final class Constants {

    // Window properties
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 600;


    // Fonts
    public static final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 48);
    public static final Font NAME_FONT = new Font("Verdana", Font.PLAIN, 24);
    public static final Font TYPE_FONT = new Font("Verdana", Font.PLAIN, 10);
    public static final Font SCORE_FONT = new Font("Verdana", Font.PLAIN, 36);
    public static final Font WIN_FONT = new Font("Verdana", Font.PLAIN, 38);
    public static final Font DEBUG_FONT = new Font("Consolas", Font.BOLD, 16);


    // Colors
    public static final Color GRID_COLOR = new Color(186, 172, 159, 255);

    public static final Color O_COLOR = new Color(129, 190, 247, 255);
    public static final Color X_COLOR = new Color(247, 129, 129, 255);

    public static final Color BACKGROUND_COLOR = new Color(250, 248, 239, 255);
    public static final Color O_TYPE_BACKGROUND_COLOR = O_COLOR.darker();
    public static final Color X_TYPE_BACKGROUND_COLOR = X_COLOR.darker();

    public static final Color HOVER_NONE_COLOR = new Color(0, 0, 0, 30);
    public static final Color CLICKED_NONE_COLOR = new Color(0, 0, 0, 50);
    public static final Color HOVER_FRIEND_COLOR = new Color(64, 255, 0, 30);
    public static final Color HOVER_ENNEMY_COLOR = new Color(250, 16, 35, 30);

    public static final Color TITLE_FONT_COLOR = new Color(100, 100, 100, 255);
    public static final Color TYPE_FONT_COLOR = Color.WHITE;
    public static final Color SEPARATOR_FONT_COLOR = new Color(180, 180, 180, 255);
    public static final Color WIN_TIE_FONT_COLOR = new Color(130, 90, 37, 255);
    public static final Color DEBUG_FONT_COLOR = new Color(0, 150, 0, 255);


    // Strokes
    public static final Stroke BASIC_STROKE = new BasicStroke(1);
    public static final Stroke LINE_STROKE = new BasicStroke(13);
    public static final Stroke MINI_LINE_STROKE = new BasicStroke(6);


    // Display variables
    public static final int PLAYERS_SHAPE_SIZE = 18;
    public static final int PLAYERS_SHAPE_NAME_X_OFFSET = 15;
    public static final int PLAYERS_NAME_TYPE_X_OFFSET = 15;
    public static final int PLAYERS_Y_OFFSET = 130;

    public static final int SCORES_SEPARATOR_OFFSET = 20;
    public static final int SCORES_Y_OFFSET = 270;

    public static final int TURN_SHAPE_SIZE = 150;
    public static final int TURN_Y_OFFSET = 340;

    public static final int WIN_SHAPE_SIZE = 80;
    public static final int WIN_TIE_Y_OFFSET = 400;
    public static final int WIN_Y_OFFSET = 490;

    public static final int DEBUG_Y_OFFSET = 20;


    // Update & render settings
    public static final double TICK_TIME = Math.pow(10,9) / 60d;
    public static final double FRAME_TIME = Math.pow(10,9) / 120d;


}
