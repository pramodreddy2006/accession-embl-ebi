package com.ebi.accession;

import java.util.Arrays;
import java.util.List;

import com.ebi.accession.util.AccessionUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AccessionTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AccessionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AccessionTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSample() {
		String[] elements = { "A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000115", "ERR000116",
				"ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "SRR211001", "ABCDEFG1" };
		try {
			List<String> expected = Arrays.asList("A00000", "A0001", "ABCDEFG1", "DRR2110012", "ERR000111-ERR000113",
					"ERR000115-ERR000116", "ERR100114", "ERR200000001-ERR200000003", "SRR211001");
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			assertEquals(expected.toString(), sol.toString());
		} catch (Exception e) {
			assert (false);
		}
	}

	public void testUnequalDigitsAndLetters() {
		String[] elements = { "A00001", "A002", "B003" };
		try {
			List<String> expected = Arrays.asList("A00001", "A002", "B003");
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			assertEquals(expected.toString(), sol.toString());
		} catch (Exception e) {
			assert (false);
		}
	}
	
	public void testAllSame() {
		String[] elements = { "A00001", "A00001", "A00001", "A00001" };
		try {
			List<String> expected = Arrays.asList("A00001");
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			assertEquals(expected.toString(), sol.toString());
		} catch (Exception e) {
			assert (false);
		}
	}
	
	public void testSameDigits() {
		String[] elements = { "A00001", "A00001", "B00001", "AB00001" };
		try {
			List<String> expected = Arrays.asList("A00001", "AB00001", "B00001" );
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			assertEquals(expected.toString(), sol.toString());
		} catch (Exception e) {
			assert (false);
		}
	}
	
	public void testIncreaseLength() {
		String[] elements = { "ABC999", "ABC1000"};
		try {
			List<String> expected = Arrays.asList("ABC1000", "ABC999");
			List<String> sol = AccessionUtil.getOrderedRangeList(elements);
			assertEquals(expected.toString(), sol.toString());
		} catch (Exception e) {
			assert (false);
		}
	}
	
	
	public void testInvalidAccession() {
		String[] elements = { "A00001A", "A000012"};
		try {
			AccessionUtil.getOrderedRangeList(elements);
			assert(false);
		} catch (Exception e) {
			assert (true);
		}
	}
	
}
