# Bowling kata

Classical Bowling kata using Java and JUnit 5.

The task is to write a score calculator. The input is a string of characters and the
output is integer points. The characters are single digits numbers 1.9, " ", "-", "/" and "X". The latter
represents no roll, zero, spare and strike, respectively. The calculator shall handle partial inputs.

See below for explanation of those terms.

At the end, there are some suggestions for testing.

## Scoring

Wikipedia: [https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring][https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring]

The most difficult part of bowling scoring to comprehend is when a strike or spare is scored, as the score on the 
scorecard does not get updated immediately.

A game consists of **ten frames**, which start with a full rack of **ten pins**. In each frame, you have two deliveries 
of your ball, in which to knock down as many of the ten pins as you can.

### Strike
If you knock down all the pins on your first ball, it is called a **strike**. The score doesn't get added on straight 
away because for a strike, you get the value of your next two balls as a bonus. 

For example, if you score a strike in the first frame, then a 7 and 1 in the second frame, you would score 18 (10+7+1) 
for the first frame, and 8 for the second frame, making a total of 26 after two frames.

### Spare
If you knock down some of the pins on the first ball, and knocked down the remainder of the pins in the second ball, 
it is known as a **spare**. Again, the score does not get added on straight away because for a spare, you get the value 
of your next ball as a bonus. 

For example, if you score a spare in the first frame, say a 6 and a 4, then got an 8 and a 1 in the second frame, 
you would score 18 (6+4+8) for the first frame, and 9 for the second frame, making a total of 27 after two frames.

### Final frame

When it comes to the **final frame**, it is slightly different. In the final frame, you get bonus balls if you strike 
or spare, to a maximum of three deliveries. 

If you strike in the first delivery you have the opportunity to strike in the remaining two and have three deliveries 
in total. If you scored strikes in each of your final three deliveries, the score for the final frame would 
be 30 (10+10+10). 

If you spare the final frame, you get the third delivery as a bonus. So, a spare, 9 and 1, followed 
by a strike would equal 20 (9+1+10).

## Suggested test cases

Remember that the final frame is different. 

### Full game cases

"X X X X X X X X X XXX" (12 rolls: 12 strikes) = 10 frames * 30 points = 300

"9-9-9-9-9-9-9-9-9-9-" (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90

"5/5/5/5/5/5/5/5/5/5/5" (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150

### Partial game

"45" = 1 Frame 4 + 5 = 9 

"3/" = 1 Frame with spare but no following = 0

"3/2" = 1 Frame with spare and a following roll = 10 + 2 = 12

"X " = 1 Frame with strike but no following = 0

"X 2" = 1 Frame with strike and a following roll = 0

"X 23" = 2 Frame with strike in first = 10 + 2 + 3 + 2 + 3 = 20

### Always true (for property testing)

The maximum points you can reach is 300. The minimum is 0. 

There are never more than 10 frames but the final frame (10th) may have three rolls.


[https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring]: https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring