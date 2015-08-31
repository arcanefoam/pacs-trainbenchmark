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

import org.apache.commons.cli.ParseException;

public class IncEvlBenchmarkMain {

	public static void main(String[] args) throws IOException, ParseException {
		IncEvlBenchmarkLogic benchmarkLogic = new IncEvlBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();
	}
}
