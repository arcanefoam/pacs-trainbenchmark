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

package hu.bme.mit.trainbenchmark.ttc.benchmark.incevl.benchmarkcases;

import hu.bme.mit.trainbenchmark.ttc.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.ttc.railway.Segment;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;

public class IncEvlPosLength extends AbstractIncEvlBenchmarkCase {

	protected IncEvlPosLength() {
		super("PosLength.evl");
	}

	@Override
	protected Collection<Object> processCheck(
			Collection<UnsatisfiedConstraint> unsatisfiedConstraints) {
		this.matches = new HashSet<Object>();
		for (UnsatisfiedConstraint uc : unsatisfiedConstraints) {
			if (uc.getInstance() instanceof Segment) {
				this.matches.add(new Match((Segment) uc.getInstance()));
			}
		}
		return this.matches;
	}

	@Override
	protected AbstractIncEvlTransformationAction getTransform() {
		return new Transformation();
	}

	private class Match extends AbstractIncEvlMatch {
		public Match(final Segment segment) {
			super();
			match = new RailwayElement[] { segment };
		}

		public Segment getSegment() {
			return (Segment) match[0];
		}

	}

	private class Transformation extends
			AbstractIncEvlTransformationAction {
		@Override
		public void transform(final Collection<Object> matches) {
			for (final Object match : matches) {
				final Match plm = (Match) match;
				final int length = plm.getSegment().getLength();
				plm.getSegment().setLength(-length + 1);
			}
		}

	}

}
