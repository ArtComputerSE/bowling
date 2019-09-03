package se.crisp.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FramesParser {

    FramesParser(String frames) {
        initCharToValueMap();
        initFrameList(frames);
    }

    private void initCharToValueMap() {
        m.put('-', 0);
        for (int i = 0; i < 10; ++i) {
            m.put((char)(i+'0'), i);
        }
    }

    private Frame sumFrame(char[] frame) {
        int sum = 0;
        for (char c:frame) {
            if (c == '/') {
                return new Frame(10, Frame.Type.SPARE);
            } else if ( c == 'X'){
                return new Frame(10, Frame.Type.STRIKE);
            }
            sum += m.get(c);
        }
        return new Frame(sum, Frame.Type.OPEN);
    }

    private String[] splitFrames(String frame) {
        return  frame.split(",");
    }

    private void initFrameList(String str) {
        String[] frames = splitFrames(str);
        for (String s: frames) {
            frameList.add(sumFrame(s.toCharArray()));
        }
    }

    private Map<Character, Integer> m = new HashMap<>();
    List<Frame> frameList = new ArrayList<>();

    public List<Frame> getFrameList() {
        return frameList;
    }
}
