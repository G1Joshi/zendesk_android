package com.example.zendesk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zendesk.answerbot.AnswerBot
import zendesk.answerbot.AnswerBotEngine
import zendesk.chat.Chat
import zendesk.chat.ChatEngine
import zendesk.core.AnonymousIdentity
import zendesk.core.Identity
import zendesk.core.Zendesk
import zendesk.messaging.Engine
import zendesk.messaging.MessagingActivity
import zendesk.support.Guide
import zendesk.support.Support
import zendesk.support.SupportEngine


class MainActivity : AppCompatActivity() {
    private val zendeskUrl: String = "";
    private val appId: String = "";
    private val oauthClientId: String ="";
    private val chatAccountKey: String ="";

    private val name: String ="";
    private val email: String ="";


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        Zendesk.INSTANCE.init(this, zendeskUrl, appId, oauthClientId);

        val identity: Identity = AnonymousIdentity.Builder()
            .withNameIdentifier(name)
            .withEmailIdentifier(email)
            .build();
        Zendesk.INSTANCE.setIdentity(identity);

        Support.INSTANCE.init(Zendesk.INSTANCE);
        AnswerBot.INSTANCE.init(Zendesk.INSTANCE, Guide.INSTANCE);
        Chat.INSTANCE.init(this, chatAccountKey);

        val answerBotEngine: Engine? = AnswerBotEngine.engine()
        val supportEngine: Engine = SupportEngine.engine()
        val chatEngine: Engine? = ChatEngine.engine()

        MessagingActivity.builder().withEngines(answerBotEngine, supportEngine, chatEngine).show(this)
    }
}