package com.wildanokt.dictionarymade.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_NAME_IE = "table_indonesia_english";
    static String TABLE_NAME_EI = "table_english_indonesia";

    static final class DictionaryCollums implements BaseColumns{
        static String HEADER_IE = "header_ie";
        static String CONTENT_IE = "content_ie";

        static String HEADER_EI = "header_ei";
        static String CONTENT_EI = "content_ei";
    }
}
