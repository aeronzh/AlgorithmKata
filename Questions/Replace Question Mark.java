// http://www.1point3acres.com/bbs/thread-184034-1-1.html
// Given a string consists of '0', '1' and '?'
// '?' can be replaced by either '0' or '1'. Print all possible replacements

public class ReplaceQuestionMark {
	public ReplaceQuestionMark(String s) {
		replace(s);
	}


	private void replace(String s) {
		if (s == null || s.length() == 0) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		search(s, sb, 0);
	}

	private void search(String s, StringBuilder sb, int pos) {
		if (s.length() == sb.length()) {
			System.out.println(sb.toString());
			return;
		}

		for (int i = pos; i < s.length(); i++) {
			if (s.charAt(i) == '?') {

				search(s, sb.append("0"), i + 1);
				sb.delete(i, i + 1);

				search(s, sb.append("1"), i + 1);
				sb.delete(i, i + 1);

			} else {
				sb.append(s.charAt(i));
			}
		}
	}
}