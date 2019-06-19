package se.crisp.bowling;

 class BowlingScorer {

     int score(String pins) {

         int total_score = 0;
         int spare_place = 0;
         String[] splited = pins.split("\\s+");
         System.out.println("Here is all frames" + pins);
         int frames = splited.length;
         System.out.println("Here is frames size" + frames);
         for (int j = 0; j < frames; j++) {
             total_score+=frame_score(splited[j],frames);
         }
         return total_score;
     }

         int frame_score(String pins,int frames) {
         int result=0;
             String[] input = pins.split("");
             System.out.println("Here is input" + input[0]);

             for (int i = 0; i < pins.length(); i++) {
                 switch (input[i]) {
                     case " ":
                         result += 0;
                         break;

                     case "-":
                         result += 0;
                         break;

                     case "/":
                         System.out.println("Here is / place" + i);
                         if (frames == 1 && pins.length() != 3) {
                             result = 0;
                         } else {
                             result += 10;
                         }
                         break;

                     case "X":
                         if (frames == 1) {
                             result = 0;
                         } else {
                             result += 10;
                         }
                         break;

                     default:
                         result += Integer.valueOf(input[i]);
                         break;
                 }
             }

             return result;
         }



 }


