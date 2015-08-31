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

package hu.bme.mit.trainbenchmark.ttc.benchmark.batchevl.benchmarkcases;

import hu.bme.mit.trainbenchmark.ttc.benchmark.emf.EMFBenchmarkCase;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.evl.EvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;

public abstract class AbstractBatchEvlBenchmarkCase extends EMFBenchmarkCase {

	protected String filename;
	protected EvlModule module;

	protected AbstractBatchEvlBenchmarkCase(String filename) {
		this.filename = filename;
	}

	@Override
	protected void init() throws IOException {
		super.init();
		try {
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	protected void destroy() throws IOException {
		this.module.reset();
	}
	
	@Override
	protected Collection<Object> check() throws IOException {
		try {
			module = new EvlModule();
			module.parse(this.getClass().getResource("/evl/" + filename).toURI());
			InMemoryEmfModel model = new InMemoryEmfModel(resource);
			this.module.getContext().getModelRepository().addModel(model);
			module.execute();
			return this.processCheck(module.getContext().getUnsatisfiedConstraints());
		} catch(Exception e) {
			throw new IOException(e);
		}	
	}

	@Override
	public void read() throws IOException {
		super.read();
	}
	
	@Override
	protected void modify(Collection<Object> matches) {
		this.getTransform().transform(matches);
	}

	@Override
	protected void registerComparator() {
		this.comparator = new BatchEvlMatchComparator();
	}
	
	protected abstract Collection<Object> processCheck(Collection<UnsatisfiedConstraint> unsatisfied);
	protected abstract AbstractBatchEvlTransformationAction getTransform();
}
