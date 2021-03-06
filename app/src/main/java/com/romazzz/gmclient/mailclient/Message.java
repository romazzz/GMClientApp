package com.romazzz.gmclient.mailclient;

import android.util.Log;

import com.google.api.services.gmail.model.MessagePartHeader;

import java.io.IOException;

/**
 * Created by z01tan on 15/04/2017.
 */

public class Message implements IMessage {
    private static final String TAG = Message.class.getSimpleName();
    private static int MESSAGE_COUNTER = 0;
    private String mFrom = "";
    private String mTo = "";
    private String mSubject = "";
    private String mText = "";
    private String mID = "";
    private String mSnippet = "";
    private boolean mIsUnread;
    private boolean mIsSent;

    public Message(String from, String to, String subject, String text, String snippet) {
        MESSAGE_COUNTER += 1;
        mID = Integer.toString(MESSAGE_COUNTER);
        mFrom = from;
        mTo = to;
        mSubject = subject;
        mText = text;
        mSnippet = snippet;
    }

    public Message(String from, String to, String subject, String text) {
        this(from, to, subject, text, "");
    }

    public Message(com.google.api.services.gmail.model.Message googleMessage) {
        try {
            Log.d(TAG, "googleMessage " + googleMessage.toPrettyString());
        } catch (IOException e) {}
        mID = googleMessage.getId();
        for(MessagePartHeader header : googleMessage.getPayload().getHeaders()) {
            if(header.getName().toLowerCase().equals("to"))
                mTo = header.getValue();
            if(header.getName().toLowerCase().equals("from"))
                mFrom = header.getValue();
            if(header.getName().toLowerCase().equals("subject"))
                mSubject = header.getValue();
            if(header.getName().toLowerCase().equals("from"))
                mFrom = header.getValue();
        }
    }

    @Override
    public String getID() {
        return mID;
    }

    @Override
    public boolean isSent() {
        return mIsSent;
    }

    @Override
    public boolean isUnread() {
        return mIsUnread;
    }

    @Override
    public String getFrom() {
        return mFrom;
    }

    @Override
    public String getTo() {
        return mTo;
    }

    @Override
    public String getSubject() {
        return mSubject;
    }

    @Override
    public String getText() {
        return mText;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    @Override
    public boolean equals(Object obj) {
        Message other = (Message) obj;
        if(other == null)
            return false;

        return this.getID() == other.getID();
    }

    public static Message convert(com.google.api.services.gmail.model.Message message ) {
        return new Message(message);
    }
}
