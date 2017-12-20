/**
 * Simple class that calls the first window. Also used as a notes page for known 
 * issues.
 * 
 * @author chris
 *
 */
public class Pokelator {

  public static void main(String[] args) {
    MainWindow main = new MainWindow();
    main.initializeWindow();
  }
}
/*
 * Some notes on my program: This program works as of this writing, with most of
 * the features that i would like it to have. There are still some unfinished
 * ideas and sections, but it would take months of work to get the program to my
 * ideal standard, so I decided to be happy with what i have. Here are some of
 * the issues I still have to deal with:
 * 
 * The IV Calc page does not reject numbers that are out of range, but only
 * because it would be a ridiculous task in Java from my research.
 * 
 * The code in the Pokemon class is jumbled, as i made it while I was still
 * learning the basics. I've tried to clean it up, but it's still pretty
 * disorganized.
 * 
 * The list of Pokemon moves shows repeats ass some pokemon can learn a move
 * twice, but by different methods.
 * 
 * My choice of layouts has screwed me over. I chose Absolute, but I should have
 * stuck with a more free flowing layout. It would be a massive time sink to
 * rewrite the dozens of textfields and buttons in a new layout, so I haven;t
 * done it yet.
 * 
 * I will attempt to address these issues where I can, but admittedly, given the
 * opportunity I'll just recreate this program in a more GUI friendly language.
 */
