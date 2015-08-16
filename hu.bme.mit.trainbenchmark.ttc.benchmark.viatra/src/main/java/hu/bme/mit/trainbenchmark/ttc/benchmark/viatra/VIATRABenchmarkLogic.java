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
package hu.bme.mit.trainbenchmark.ttc.benchmark.viatra;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.ttc.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.ttc.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.ttc.benchmark.scenarios.AbstractBenchmarkLogic;
import hu.bme.mit.trainbenchmark.ttc.benchmark.viatra.benchmarkcases.VIATRABenchmarkCaseFactory;

public class VIATRABenchmarkLogic extends AbstractBenchmarkLogic {

	protected VIATRABenchmarkConfig vbc;

	public VIATRABenchmarkLogic(final String[] args) throws ParseException {
		super();
		benchmarkConfig = new VIATRABenchmarkConfig(args);
	}

	public VIATRABenchmarkLogic(final BenchmarkConfig bc) {
		super();
		this.benchmarkConfig = bc;
	}

	@Override
	protected AbstractBenchmarkCase getBenchmarkCase(final String query) {
		final VIATRABenchmarkCaseFactory factory = new VIATRABenchmarkCaseFactory();
		return factory.create(query);
	}

}
