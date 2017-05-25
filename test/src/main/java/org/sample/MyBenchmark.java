/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class MyBenchmark {

	@State(Scope.Thread)
	public static class MyState {
		public List<Integer> veryLargeList = new ArrayList<>();
		public List<Integer> largeList = new ArrayList<>();
		public List<Integer> mediumList = new ArrayList<>();
		public List<Integer> smallList = new ArrayList<>();
		public List<Integer> verySmallList = new ArrayList<>();

		public MyState() {
			for (int i = 0; i < 999999999; i++) {
				veryLargeList.add(i);
			}

			for (int i = 0; i < 9999999; i++) {
				largeList.add(i);
			}

			for (int i = 0; i < 9999; i++) {
				mediumList.add(i);
			}

			for (int i = 0; i < 500; i++) {
				smallList.add(i);
			}

			for (int i = 0; i < 5; i++) {
				verySmallList.add(i);
			}

		}

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testSeqentialVeryLargeStreamMethod(MyState state) {

		state.veryLargeList.stream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testParellelVeryLargeStreamMethod(MyState state) {

		state.veryLargeList.parallelStream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testSeqentialLargeStreamMethod(MyState state) {

		state.largeList.stream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testParellelLargeStreamMethod(MyState state) {

		state.largeList.parallelStream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testSeqentialMediumStreamMethod(MyState state) {

		state.mediumList.stream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testParellelMediumStreamMethod(MyState state) {

		state.mediumList.parallelStream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testSeqentialSmallStreamMethod(MyState state) {

		state.smallList.stream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testParellelSmallStreamMethod(MyState state) {

		state.smallList.parallelStream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testSeqentialVerySmallStreamMethod(MyState state) {

		state.verySmallList.stream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testParellelVerySmallStreamMethod(MyState state) {

		state.verySmallList.parallelStream().filter(i -> (0 == i % 2)).collect(Collectors.toList());

	}
}
