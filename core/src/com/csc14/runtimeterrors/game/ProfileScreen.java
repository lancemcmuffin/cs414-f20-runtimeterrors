package com.csc14.runtimeterrors.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;

public class ProfileScreen implements Screen {
    private final OmegaChess parent;
    private final Stage stage;
    //private TextButton changePwBtn, changeNicknameBtn; todo decide if implement or remove
    private String nickname;
    private boolean isPopupDisplayed = false;

    public ProfileScreen(OmegaChess omegachess){
        parent = omegachess;     // setting the argument to our field.
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        nickname = parent.getUser();

        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        style.font.getData().setScale(3f);

        // set up profile label
        TextField profileLabel = new TextField("User Profile!", style);
        profileLabel.setWidth(400);
        profileLabel.setPosition(200, 420);
        stage.addActor(profileLabel);

        // add relevant nickname widgets to the screen
        addNicknameToStage();

        // add relevant statistic info to the screen
        addStatsInfoToStage();

        // add buttons to the screen
        addButtonsToStage();
    }

    private void addButtonsToStage() {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        TextButton unregisterBtn = new TextButton("Unregister", skin);
        TextButton lobbyBtn = new TextButton("Lobby", skin);
        TextButton mailboxBtn = new TextButton("Mailbox", skin);
        TextButton historyBtn = new TextButton("History", skin);

        // // todo decide if implement or remove
        //changePwBtn = new TextButton("Change Password", skin);
        //changeNicknameBtn = new TextButton("Change Nickname", skin);

        // set up change nickname button
        /*changeNicknameBtn.setTransform(true);
        changeNicknameBtn.setScale(0.4f);
        changeNicknameBtn.setPosition(30, 180);
        stage.addActor(changeNicknameBtn);

        // set up change password button
        changePwBtn.setTransform(true);
        changePwBtn.setScale(0.4f);
        changePwBtn.setPosition(30, 130);
        stage.addActor(changePwBtn); */

        // set up mailbox button
        mailboxBtn.setTransform(true);
        mailboxBtn.setScale(0.4f);
        mailboxBtn.setPosition(30, 130);
        stage.addActor(mailboxBtn);

        // set up history button
        historyBtn.setTransform(true);
        historyBtn.setScale(0.4f);
        historyBtn.setPosition(30, 80);
        stage.addActor(historyBtn);

        // set up unregister button
        unregisterBtn.setTransform(true);
        unregisterBtn.setScale(0.4f);
        unregisterBtn.setPosition(30, 30);
        stage.addActor(unregisterBtn);

        // set up lobby button
        lobbyBtn.setTransform(true);
        lobbyBtn.setScale(0.4f);
        lobbyBtn.setPosition(450, 15);
        stage.addActor(lobbyBtn);

        // unregister button will handle unregistering the user and returning to main menu screen
        unregisterBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // first bring confirmation box to make sure user wants to unregister
                int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to" +
                                " unregister? This action cannot be undone.", "Confirm Unregister",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    // unregister
                    parent.getClient().sendUnregisterRequest(nickname);
                    parent.changeScreen(OmegaChess.SCREEN.MAIN_MENU);
                }
            }
        });

        // history button will return user to archive screen
        historyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.ARCHIVE);
            }
        });

        // back button will return user to main menu screen
        lobbyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.LOBBY);
            }
        });

        // mailbox button will take user to mailbox screen
        mailboxBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(OmegaChess.SCREEN.MAILBOX);
            }
        });

        // todo decide if implementing this or not
        // change password button will handle changing the user's password
        /*changePwBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            };
        });

        // change nickname button will handle changing the user's nickname
        changeNicknameBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            };
        });*/
    }

    private void addStatsInfoToStage() {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        String totalGames = "-", gamesWon = "-", gamesLost = "-", gamesDraw = "-";

        // send request to get profile data
        OCMessage receivedMessage = parent.getClient().sendGetProfileDataRequest(nickname);

        if(receivedMessage.get("success").equals("true"))
        {
            gamesWon = receivedMessage.get("gamesWon");
            gamesLost = receivedMessage.get("gamesLost");
            gamesDraw = receivedMessage.get("gamesTied");

            totalGames = String.valueOf(Integer.parseInt(gamesWon) + Integer.parseInt(gamesLost) +
                    Integer.parseInt(gamesDraw));
        }

        Label.LabelStyle style_label = new Label.LabelStyle();
        style_label.font = new BitmapFont();
        style_label.fontColor = Color.PURPLE;
        style_label.font.getData().setScale(2f);

        Label totalGamesLabel = new Label("Total Games Played:", style_label);
        Label totalGamesText = new Label(totalGames, skin);
        totalGamesText.setFontScale(2f);

        Label wonLabel = new Label("Won:", style_label);
        Label wonText = new Label(gamesWon, skin);
        wonText.setFontScale(2f);

        Label lostLabel = new Label("Lost:", style_label);
        Label lostText = new Label(gamesLost, skin);
        lostText.setFontScale(2f);

        Label drawLabel = new Label("Draw:", style_label);
        Label drawText = new Label(gamesDraw, skin);
        drawText.setFontScale(2f);

        totalGamesLabel.setWidth(40);
        totalGamesLabel.setPosition(80, 300);
        stage.addActor(totalGamesLabel);

        totalGamesText.setWidth(10);
        totalGamesText.setPosition(360, 310);
        stage.addActor(totalGamesText);

        wonLabel.setWidth(40);
        wonLabel.setPosition(35, 250);
        stage.addActor(wonLabel);

        wonText.setWidth(10);
        wonText.setPosition(110, 260);
        stage.addActor(wonText);

        lostLabel.setWidth(40);
        lostLabel.setPosition(230, 250);
        stage.addActor(lostLabel);

        lostText.setWidth(10);
        lostText.setPosition(305, 260);
        stage.addActor(lostText);

        drawLabel.setWidth(40);
        drawLabel.setPosition(440, 250);
        stage.addActor(drawLabel);

        drawText.setWidth(10);
        drawText.setPosition(515, 260);
        stage.addActor(drawText);
    }

    private void addNicknameToStage() {
        Label.LabelStyle style_label = new Label.LabelStyle();
        style_label.font = new BitmapFont();
        style_label.fontColor = Color.PURPLE;
        style_label.font.getData().setScale(2f);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label nicknameLabel = new Label("Nickname:", style_label);
        Label nicknameText = new Label(nickname, skin);
        nicknameText.setFontScale(2f);

        nicknameLabel.setWidth(20);
        nicknameLabel.setPosition(80, 350);
        stage.addActor(nicknameLabel);

        nicknameText.setWidth(60);
        nicknameText.setPosition(250, 360);
        stage.addActor(nicknameText);
    }

    public void showNotification(String message, int messageCount){
        isPopupDisplayed = true;
        String title = "New Notification!";

        if( messageCount > 1 )
        {
            title = "New Notifications!";
        }

        JOptionPane.showMessageDialog(null, message,
                title, JOptionPane.INFORMATION_MESSAGE);
        isPopupDisplayed = false;
    }

    public boolean isPopupShown(){
        return isPopupDisplayed;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose() {
    }
}
