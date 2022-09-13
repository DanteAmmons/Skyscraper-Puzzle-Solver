# Skyscraper-Puzzle-Solver
solves these puzzles: https://www.puzzle-skyscrapers.com/

rn all i have is the variant that solves the weekly one. my next goal is to be able to custom set the puzzle size. after that, maybe convert this to javascript so i can implement it in a userscript.
aside from that i should probably clean up the code and actually have it parse console inputs.
it's probably pretty shitty code but idc. and if u do you can suck upon my pussy like its a purple push pop

EDIT 9/13/2022 #1: now it takes text input

EDIT 9/13/2022 #2: finally figured out how global vars in java work :) 
created a program that solves all sizes of skyscrapers puzzles. however, it doesn't really work, and i've shown the output board matrix and guesses matrix of the boards that the program doesn't solve. 
it's worth noting the test cases where it doesn't work all come from the hard versions of each size of skyscrapers puzzle, which are found at https://www.puzzle-skyscrapers.com/?size=2 https://www.puzzle-skyscrapers.com/?size=5 and https://www.puzzle-skyscrapers.com/?size=8

i think i know how i want to tackle this issue, and it's implementing a method that checks if the puzzle is complete, and if it isn't, picking one cell that only has 2 valid guesses, arbitrarily choosing one of those, 2 guesses, and riding out that guess until an error is flagged
