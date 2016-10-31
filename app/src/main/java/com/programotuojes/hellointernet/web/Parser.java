package com.programotuojes.hellointernet.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    private String html;
    private List<String> titles = new ArrayList<>();    // ID 1
    private List<String> dates = new ArrayList<>();     // ID 2
    private List<String> audios = new ArrayList<>();    // ID 3
    private List<String> showNotes = new ArrayList<>(); // ID 4

    List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>();

        parse("<title>(.*?)</title>", 1);
        parse("<pubDate>(.*?)</pubDate>", 2);
        parse("enclosure url=\"(.*?)\" type=", 3);
        parse("(?s)CDATA\\[(.*?)]", 4);

        titles.remove(0);

        for (int i = 0; i < titles.size(); i++)
            entries.add(new Entry(titles.get(i), dates.get(i), audios.get(i), showNotes.get(i)));

        return entries;
    }

    private void parse(String regex, int id) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String line = matcher.group(1);
            // Remove whitespaces
            line = line.trim().replaceAll("H.(\\s)I", "H.I");
            // Remove crappy chars
            line = line.trim().replaceAll("#x26;", "");
            // Remove time
            line = line.trim().replaceAll(" (\\d*):(\\d*):(\\d*) \\+0000", "");

            switch (id) {
                case 1:
                    titles.add(line);
                    break;
                case 2:
                    dates.add(line);
                    break;
                case 3:
                    audios.add(line);
                    break;
                case 4:
                    showNotes.add(line);
                    break;
            }
        }
    }

    Parser(String html) {
        this.html = html;
    }
}
