/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.ttc.benchmark.incevl;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.ttc.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.ttc.benchmark.incevl.benchmarkcases.IncEvlBenchmarkCaseFactory;
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.ttc.benchmark.util.BenchmarkResult;

import org.apache.commons.cli.ParseException;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.incremental.dom.TraceConstraint;

public class IncEvlBenchmarkLogic extends AbstractBenchmarkLogic {

	public IncEvlBenchmarkLogic(final String[] args) throws ParseException {
		super();
		benchmarkConfig = new IncEvlBenchmarkConfig(args);
	}

	public IncEvlBenchmarkLogic(final BenchmarkConfig bc) {
		super();
		this.benchmarkConfig = bc;
	}

	@Override
	public BenchmarkResult runBenchmark() throws IOException {
		BenchmarkResult result = super.runBenchmark();
		System.out.println(TraceConstraint.count);
		return result;
	}
	
	@Override
	protected AbstractBenchmarkCase getBenchmarkCase(final String query) {
		final IncEvlBenchmarkCaseFactory factory = new IncEvlBenchmarkCaseFactory();
		return factory.create(query);
	}

}
