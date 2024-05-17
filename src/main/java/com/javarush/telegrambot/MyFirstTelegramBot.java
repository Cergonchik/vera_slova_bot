package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "vera_slova_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "7080860457:AAGTlw1f_aYDRqauWVMY0-wnzgoMxtZ-l_o"; //TODO: добавьте токен бота в кавычках
    private HashMap<String, String> listUsers = new HashMap<>();
    private List<String> listName = new ArrayList<>();
    private List<String> allText = new ArrayList<>();
    private int ind = 0;

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // ообщение о начале игры String.format("%s",

        boolean flag = allText.contains(getMessageText());

        if (ind == 3 && !flag ) {
            sendPhotoMessageAsync(listUsers.get(listName.get(0)));
            sendTextMessageAsync("Вводи слово " + listName.get(0));

            allText.add(getMessageText());
            ++ind;

        } else if (ind == 4 && !flag) {
            sendPhotoMessageAsync(listUsers.get(listName.get(1)));
            sendTextMessageAsync("Вводи слово " + listName.get(1));

            allText.add(getMessageText());
            --ind;

        } else if (flag) {
            sendTextMessageAsync("Такое слово уже есть");
        }

        /*else if ((ind == 3 || ind == 4) && !getCallbackQueryButtonKey().equals("botton_3")  ){
            if (ind == 3) {
                ++ind;
            } else {
                --ind;
            }
            sendTextMessageAsync("Такое слово уже есть");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Нажми и пробуй еще раз!", "botton_3"));
        }*/

        if (getMessageText().equals("/start")) {
            sendPhotoMessageAsync("techer_1");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("ДАЛЕЕ!", "botton_1"));
        } else if (ind == 1) {
            sendPhotoMessageAsync("techer_2");
            sendTextMessageAsync(STEP_4_TEXT, Map.of("ДАЛЕЕ!", "botton_2"));
            ++ind;
        }

        if (getCallbackQueryButtonKey().equals("botton_1")) {

            sendPhotoMessageAsync("team_1");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Панда", "panda",
                            "Мстер Шифу", "master",
                            "Обезьяна", "monkey",
                            "Змея", "snake",
                            "Тигрица", "tiger",
                            "Богомол", "bogomol",
                            "Цапля", "heron"
                    ));
            ++ind;
        } else if (getCallbackQueryButtonKey().equals("botton_2")) {
            sendPhotoMessageAsync("team_1");
            sendTextMessageAsync(STEP_3_TEXT,
                    Map.of("Панда", "panda",
                            "Мстер Шифу", "master",
                            "Обезьяна", "monkey",
                            "Змея", "snake",
                            "Тигрица", "tiger",
                            "Богомол", "bogomol",
                            "Цапля", "heron"
                    ));
            ++ind;
        }


        switch (getCallbackQueryButtonKey()) {
            case "panda":
                listUsers.put("Панда", "panda");
                listName.add("Панда");
                break;
            case "master":
                listUsers.put("Мастер", "master");
                listName.add("Мастер");
                break;
            case "monkey":
                listUsers.put("Обезьяна", "monkey");
                listName.add("Обезьяна");
                break;
            case "snake":
                listUsers.put("Змея", "snake");
                listName.add("Змея");
                break;
            case "tiger":
                listUsers.put("Тигрица", "tiger");
                listName.add("Тигрица");
                break;
            case "bogomol":
                listUsers.put("Богомол", "bogomol");
                listName.add("Богомол");
                break;
            case "heron":
                listUsers.put("Цапля", "heron");
                listName.add("Цапля");
                break;
        }

        if (getMessageText().equals("show")) {
            sendTextMessageAsync(listName.get(0) + " " + listName.get(1) + "\n" + "ind " + ind);
            for (int i = 0; i < allText.size(); i++) {
                sendTextMessageAsync(String.valueOf(allText.get(i)) + "\n");
            }
        }

    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
/* Редактирование смс от пользователя
|| getMessageText().equals("/glory")

if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Привет, будущий программист Сергей.");
        } else if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("Asta la vista, baby!");
            return;
        }

        sendTextMessageAsync("Ваше любимое животное? ",
                Map.of("Кот", "cat", "Собака", "dog"));

        if (getCallbackQueryButtonKey().equals("cat")) {
            sendPhotoMessageAsync("step_4_pic");
        } else if (getCallbackQueryButtonKey().equals("dog")) {
            sendPhotoMessageAsync("step_6_pic");
        }


if (getMessageText().equals("smile")) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText() + " :) ");
        }

 */