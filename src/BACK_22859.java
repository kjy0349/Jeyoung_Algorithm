import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BACK_22859 {
	
	static Deque<String> tagChk;
	static StringBuilder sb;
	static StringBuilder output;
	private static String[] getLine(String tag) {
		String trimGguk = tag.substring(1, tag.length() - 1);
		String[] tags = trimGguk.split(" ");
		return tags;
	}
	
	private static String doTag(String[] tags) {
		String tag = tags[0];
		if (tags.length > 1) { // elem이 있는 tag들
			if (tag.equals("div")) {
				output.append("title : " + tags[1].split("\"")[1]).append("\n");
			}
			return "";
		} else return tag;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> elem = new ArrayList<>();
		String input = br.readLine();
 		int i = 0;
		int subIdx = 0;
		tagChk = new ArrayDeque<>();
		output = new StringBuilder();
		char[] tmp;
		sb = new StringBuilder();
		while (i < input.length()) {
			if (input.charAt(i) == '<') { // 태그 시작
				subIdx = i;
				while (input.charAt(subIdx) != '>') subIdx++;
				tmp = new char[subIdx - i + 1];
				for (int idx = i; idx <= subIdx; idx++) {
					tmp[idx - i] = input.charAt(idx);
				}
				i = subIdx;
				String[] tags = getLine(new String(tmp));
				String tag = doTag(tags);
				if (!tag.equals("")) { // div처럼 안에 요소 있는 애들 거름
					if (tagChk.isEmpty()) { // 스택에 어떤 태그도 없을 경우(새로운 태그 시작)
						tagChk.push(tag);
					} else {
						String target = tagChk.peek();
						if (tag.equals("/" + target)) { // 기존에 스택에 넣어둔 태그의 닫는 태그가 들어오면 출력. 아니면 계속 넣어주기
							if (target.equals("p")) {								
								String trimedStr = sb.toString().trim().replaceAll(" +", " ") + "\n";
								output.append(trimedStr);
								sb = new StringBuilder();
							} else if (target.equals("main")) break; //main 닫는태그 들어오면 컷
							tagChk.pop(); // p 아닌애 여는 태그/닫는 태그 들어오면 버림
						} else {
							if (!target.equals("p")) tagChk.push(tag);
						}
					}
				}
			} else {
				sb.append(input.charAt(i));
			}
			i++;
		}
		System.out.print(output);
	}
}
