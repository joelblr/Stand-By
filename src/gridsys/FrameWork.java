package gridsys;

import design.*;
import java.util.*;


public class FrameWork extends Colorify {


	final static int factor;
	final static String prefix;

	final static String leftR, rightR, mid;
	final static String leftI, rightI;
	final static String leftT, rightT;
	final static String leftL, rightL;



	static {
		prefix = " ".repeat(8);
		factor = 3 + 0 + 0 + 3;
		String fg_box = fg_blue;

		mid = bold + fg_box + "─" + reset;/** @Version1 "╭─╮","╰─╯", @Version2 "┌─┐","└─┘"  */
		leftR = prefix + bold + fg_box + "╭" + reset;	rightR = bold + fg_box + "╮" + reset;
		leftT = prefix + bold + fg_box + "├" + reset;	rightT = bold + fg_box + "┤" + reset;
		leftI = prefix + bold + fg_box + "│   "  + reset;	rightI = bold + fg_box + "   │" + reset;
		leftL = prefix + bold + fg_box + "╰" + reset;	rightL = bold + fg_box + "╯" + reset;
	}


	/**
	 * @info Returns a {@code Joined String} with delimiter
	 * @param   delimiter  :  Char/String to be added with each Element.
	 * @param	elements   :  String varargs to be attached with Delimiter.
	 * @usage 	Design.joinWith(String delimiter, String ... elements); <p>
	 * 			delimiter: a {@code String} <p>
	 * 			elements : a {@code String varargs} "elem 1", "elem 2", "elem 3"... , "elem N"
	 * @example	Design.joinWith(" + ", "a", "b", "c"); <p>
	 * @output  a + b + c
	*/
	public static String joinWith(String delimiter, String ... elements) {
		return String.join(delimiter, elements);
	}


	/**
	 * @Info Prints the Input Message in a Box Format
	 * @param   msg   ->  Messages
	 * @usage 	DLabel( msg, tag, color format ); <p>
	 * 			msg: a {@code String} <p>
	 * 			code: a {@code String} "top", "base", "joint", "blank", "tag", ""(nothing)
	 * 				and enclosed in {@code Aungular brackets}
	 * @example
	 * 				Design.printBox(0,
			<br>		new DLabel("", "(top)", ""),
			<br>		new DLabel("Casio fx-991+ Setup", "(tag)", "Y"),
			<br>		new DLabel("", "(joint)", ""),
			<br>		new DLabel(" *1*  Degree", "", "C"),
			<br>		new DLabel(" *2*  Radian", "", "C"),
			<br>		new DLabel(" *3*  Fraction Format", "", "C"),
			<br>		new DLabel(" *4*  Decimal Format", "", "C"),
			<br>		new DLabel(" *0*  Close Setup", "", "C"),
			<br>		new DLabel(" ENTER F(0 ~ 9) TO SET PRECISION", "", "W PF C P0 C P9 C) W"),
			<br>		new DLabel("", "(joint)", ""),
			<br>		new DLabel("Enter Choice:  ", "(input)", "P"),
			<br>		new DLabel("", "(base)", "")
	 */

	/***TODO TODO XXX
	public static List<String> printGrid(int width, List<DLabel> ... labels) {

		List<Integer[]> cursorEof = new LinkedList<Integer[]>();

		if (width == 0)		width = findMaxWidth(labels);

		int N = labels.length, lines = 1;
		for (int i = 0; i < N; i++) {

			lines++;

			switch (labels[i].getCode()) {

			case "<top>" :
				printTop(width);	continue;

			case "<tag>" :
				if (i == 0)		System.out.println();
				printTag(width, labels[i]);
				continue;

			case "<blank>" :
				printBlank(width);	continue;

			case "<joint>" :
				printJoint(width);	continue;

			case "<base>" :
				printBase(width);	continue;

			case "<input>" :
				int len = prefix.length() + factor/2+1 + labels[i].getTxt().length();
				cursorEof.add(new Integer[] {i, len});

			default :
				printFrame(width, labels[i]);
			}

		}//lines++;

		List<String> stdin = new LinkedList<String>();
		if (! cursorEof.isEmpty())
			stdin = getStdin(cursorEof, lines);

		return stdin;
	}**///


	/**
	 * @Info Input Processor */
	private static List<String> getStdin(List<Integer[]> inputs, int lines) {

		int i = lines;
		List<String> stdin = new LinkedList<String>();
		System.out.print("\033["+(i-inputs.get(0)[0])+"A");

		i = inputs.get(0)[0] -1;
		for (Integer[] arr : inputs) {

			if ((arr[0]-i) != 0) {
				System.out.print("\033["+(arr[0]-i)+"B");
			}
			System.out.print("\033["+arr[1]+"C");
			System.out.print(fg_green + bold);
			stdin.add(Cache.scan.nextLine());
			System.out.print(reset);

			i = arr[0]+1;
		}

		System.out.print("\n".repeat(lines - i -1));

		return stdin;
	}


	/**
	 * @info Finds the Appropriate Width of the Box
	 * @param  labels
	*/
	private static int findMaxWidth(DLabel ... labels) {
		int max_size = 0;
		for (DLabel dl : labels) {
			int len = dl.getTxt().length();
			if (dl.getCode() == "<input>")
				len += 4;
			max_size = Math.max(len, max_size);

		}return max_size;
	}


	/**
	 * @info ╭────────────────────────────────────╮ <p>
	 * Prints the Top-Part of the Message Box
	 * @param   width  -> width of the Message Box
	 */
	private static void printTop(int width) {
		System.out.println(leftR + mid.repeat(width + factor) + rightR);
	}


	/**
	 * @info ╭─ This is a Tag Msg ─ Tag2 ───────╮ <p>
	 * Prints the Top-Part of the Message Box
	 * @param   width  -> width of the Message Box
	 */
	private static void printTag(int width, DLabel ... tagLabel) {

		if (width == 0)
			width = findMaxWidth(tagLabel);

		/**@deprecated
		 * @version Old, Single Tag in One-Line
		for (DLabel tag : tagLabel) {
			int spaceLen = tag.getTxt().length();
			String cTag = colourMsg(tag);
			System.out.println(leftR+mid+ " "+cTag+" " +mid.repeat(width+factor-spaceLen-3)+rightR);
		} @deprecated*/

		/**@version New, Multiple Tags in One-Line */
		for (DLabel tag : tagLabel) {
			int spaceLen = 0;
			String cTag = "";
			String txt = tag.getTxt();
			String clr = tag.getColor();
			for (String t : txt.split("\t")) {
				if (t.length() == 0) {
					spaceLen += 2;
					cTag += mid.repeat(2);
				}
				else {
					spaceLen += t.length() + 3;
					cTag += mid+ " "+colourMsg(new DLabel(t, "", clr)) + " ";
				}
			}
			cTag = cTag.substring(15);
//			spaceLen += 1;
			System.out.println(leftR+mid+ " "+cTag + mid.repeat(width+factor-spaceLen)+rightR);
		}

	}


	/**
	 * @info │....................................│ <p>
	 * Prints a Blank Line in the Message Box
	 * @param   width  -> width of the Message Box
	 */
	private static void printBlank(int width) {
		System.out.println(leftI + " ".repeat(width) + rightI);

	}


	/**
	 * @info │       Some Message to display      │ <p>
	 * Prints the Message-Part of the Message Box
	 * @param   width    -> width of the Message Box
	 * @param   message  -> message of the Message Box
	 */
	private static void printFrame(int width, DLabel label) {

		if (width == 0)
			width = findMaxWidth(label);

		int spaceLen = width - label.getTxt().length();
		String cTxt = colourMsg(label);
		System.out.println(leftI + cTxt + " ".repeat(spaceLen) + rightI);
	}


	/**
	 * @info ├────────────────────────────────────┤ <p>
	 * Prints the Joint-Part b/w 2 segments of the Message Box
	 * @param   width    -> width of the Message Box
	 */
	private static void printJoint(int width) {
		System.out.println(leftT+mid.repeat(width+factor)+rightT);
	}


	/**
	 * @info ╰────────────────────────────────────╯ <p>
	 * Prints the Bottom-Part of the Message Box
	 * @param   width    -> width of the Message Box
	 */
	private static void printBase(int width) {
		System.out.println(leftL+mid.repeat(width+factor)+rightL);
	}




	/**
	 * @Info Test Runs */
	public static void main(String[] args) {

		Design.printBox(0,
				new DLabel("", "<top>", ""),
				new DLabel("Deg\tCasio fx-991+ Setup\t\tDDD", "<tag>", "R"),
				new DLabel("", "<joint>", ""),
				new DLabel(" *1*  Degree", "", "C"),
				new DLabel(" *2*  Radian", "", "C"),
				new DLabel(" *3*  Fraction Format", "", "C"),
				new DLabel(" *4*  Decimal Format", "", "C"),
				new DLabel(" *0*  Close Setup", "", "C"),
				new DLabel(" ENTER F(0 ~ 9)", "", "G PF C P0 C P9 C) W"),
				new DLabel("", "<joint>", ""),
				new DLabel("Enter Choice  TO SET PRECISION: ", "<input>", "P"),
				new DLabel("", "<base>", "")
			);

//		Design.printBox(
//				"╭─╮","╰─╯",
//				"│J│",
//				"│E│",
//				"│S│",
//				"│U│",
//				"│S│",
//				"╰─╯",
//				"┌─┐","└─┘"
//				"│J│",
//				"│E│",
//				"├S┤",
//				"│U│",
//				"├S┤",
//				"└─┘"
//			);
	}






}
