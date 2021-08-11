package com.java.learn.enumtest;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorEnumTest {

	@Test
	public void testGetNameInt() {
		assertEquals(ColorEnum.getName(1), "红色");
		assertEquals(ColorEnum.getName(2), "绿色");
	}

	@Test
	public void testGetName() {
		assertEquals(ColorEnum.RED.getIndex(), 1);
		assertEquals(ColorEnum.BLANK.getName(), "白色");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIndex() {
		fail("Not yet implemented");
	}

}
