package se.crisp.bowling;

 class BowlingScorer {

    int score(String pins) {

        int result=0;
        int spare_place=0;
        String[] splited = pins.split("\\s+");
        System.out.println("Here is all frames"+pins);
        int frames=splited.length;
        System.out.println("Here is frames size"+frames);
        String[] input = splited[0].split("");
        System.out.println("Here is input"+input[0]);
        for(int i=0;i<pins.length();i++) {
            switch (input[i]) {
                case " ":
                    result+=0;
                    break;

                case "-":
                    result+=0;
                    break;

                case "/":
                    System.out.println("Here is / place"+i);
                    if(frames==1 && i==1){
                        result=0;
                    }
                    else {
                        result += 10;
                    }
                    break;

                case "X":
                    result+=0;
                    break;

                default:
                    result+=Integer.valueOf(input[i]);
                    break;
            }
        }

        return result;
    }

}
