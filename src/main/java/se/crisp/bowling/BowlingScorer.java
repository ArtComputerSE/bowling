package se.crisp.bowling;

 class BowlingScorer {

    int score(String pins) {
        int result=0;
        boolean strike=false;
        String[] input = pins.split("");
        for(int i=0;i<pins.length();i++) {
            switch (input[i]) {
                case " ":
                    result+=0;
                    break;

                case "-":
                    result+=0;
                    break;

                case "/":
                    result+=0;
                    break;

                case "X":
                    strike=true;
                    result+=0;
                    break;

                default:
                    result+=Integer.valueOf(input[i]);
                    break;
            }
        }
        if(strike) {
            return result+10;
        }
        return result;
    }

}
