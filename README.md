# Skyscraper-Puzzle-Solver
solves these puzzles: https://www.puzzle-skyscrapers.com/

rn all i have is the variant that solves the weekly one. my next goal is to be able to custom set the puzzle size. after that, maybe convert this to javascript so i can implement it in a userscript.
aside from that i should probably clean up the code and actually have it parse console inputs.
it's probably pretty shitty code but idc. and if u do you can suck upon my pussy like its a purple push pop

EDIT #1 (9/13/22): now it takes text input

EDIT #2 (9/13/22): finally figured out how global vars in java work :) 
created a program that solves all sizes of skyscrapers puzzles. however, it doesn't really work, and i've shown the output board matrix and guesses matrix of the boards that the program doesn't solve. 
it's worth noting the test cases where it doesn't work all come from the hard versions of each size of skyscrapers puzzle, which are found at https://www.puzzle-skyscrapers.com/?size=2 https://www.puzzle-skyscrapers.com/?size=5 and https://www.puzzle-skyscrapers.com/?size=8

i think i know how i want to tackle this issue, and it's implementing a method that checks if the puzzle is complete, and if it isn't, picking one arbitrary cell that only has 2 valid guesses, arbitrarily choosing one of those 2 guesses, and riding out that guess until an error is flagged

EDIT #3 (9/13/22): so i implemented the algorthim i was talking about up there, and good news, it solves failed test cases 1 and 3, but 2 remains unsolved. :( 
the way i implemented that algorithm was such that the arbitrary 2-guess cell is the first one an iterator comes across going column by column, and the arbitrary hypothetical value chosen is the lowest of the 2 guesses. 
the only cases i really cover in my code is if the hypothetical leads to an invalid solution (i.e. one of the row/column permutation cadidate array lists takes on a size of 0) or if it solves the puzzle. 
it's basically just guessing, which probably isn't the most mathematical way to solve this, but in coding this i'm trying to replicate my way of thinking when trying to solve these puzzles.
however, i have no case for when the hypothetical is also inconclusive, which is what happens in failed test case #2.

so what i think i'm going to do next is try and take everything i've written and implement it as a recursion tree of sorts. 
sort of a "oh, is the value at (3,1) not 2? then it's 4. does inputting 2 lead to the solution? great! is inputting 2 inconclusive? find the next 2-guess cell upon inputting 2. if neither guess works, it's not 2. if one of them works, whoopee. if it's inconclusive, rinse and repeat." 
it feels like a kinda cheap way of solving this but i think it'll work
