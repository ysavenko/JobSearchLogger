package com.giraffe.jsl.jobcontact;

import java.util.Stack;

class Solution
{
	public String removeStars(String s)
	{
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < result.length(); i++)
		{
			if (result.charAt(i) == '*')
			{
				result.delete(i - 1, i + 1);
			} 
			else
			{
				i++;
			}
		}
		return result.toString();
	}

	public static void main(String[] args)
	{
		long[] arr1 = new long[]
		{ 0, 1, 2, 3, 4, 5, 6, 7 };
		long[] arr2 = new long[]
		{ 0, 2, 6, 8, 9 };

//		arr1 = new long[]{1, 35};
//		arr2 = new long[]{6, 9, 13, 15, 20, 25, 29, 46};
		System.out.println(celebrity(new int[][]
		{
				{ 0, 0, 0, 1, 0 },
				{ 1, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0 }

		}, 5));
	}

}