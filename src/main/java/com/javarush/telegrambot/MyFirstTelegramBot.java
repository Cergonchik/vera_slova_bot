package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_demo_mentor_777_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "7010235483:AAFVAsQkoT1SpZsHIbvz2fgBfiIgSNhO-B0"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // ообщение о начале игры
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
           sendTextMessageAsync(STEP_1_TEXT , Map.of("Взлом холодильника","step_1_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! +20 славы", "step_2_btn")
            );
        }

        // взламываем робот пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса","step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Отправить робот за едой! +30 славы", "step_4_btn",
                            "Взять рыбку! +30 славы", "step_4_btn",
                            "Скинуть банку с огурцами! +30 славы", "step_4_btn")
            );
        }

        // взламываем камеру go pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Взлом камеры go pro","step_5_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Снять видео на камеру! +40 славы", "step_6_btn",
                            "Сделать фото! +40 славы", "step_6_btn",
                            "Очистить флешку на камере! +40 славы", "step_6_btn")
            );
        }

        // взламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом компьютера","step_7_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("Написать код на Java! +50 славы", "step_8_btn",
                            "Написать Web приложение! +50 славы", "step_8_btn",
                            "Выключить компьютер и пойти спать! +50 славы", "step_8_btn")
            );
        }

        // хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT + "Заработанные очки кота, " + getUserGlory() + " можно похвастаться дворовым котам!"  );
        }

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Очки славы кота: " + getUserGlory());
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