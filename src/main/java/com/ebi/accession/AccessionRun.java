package com.ebi.accession;

import java.util.List;
import java.util.Scanner;

import com.ebi.accession.util.AccessionUtil;

public class AccessionRun {

	// A00000, A0001, ERR000111, ERR000112, ERR000113, ERR000115, ERR000116,
	// ERR100114, ERR200000001, ERR200000002, ERR200000003, DRR2110012,
	// SRR211001, ABCDEFG1

	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			String input = in.nextLine();
			String[] elements = input.split(",");
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			System.out.println();
			System.out.println(sol);
			in.close();
		} catch (Exception e) {
			System.out.println("******* ERROR *******");
			System.out.println(e.getMessage());
		} finally {
			if (in != null)
				in.close();
		}
	}

}
