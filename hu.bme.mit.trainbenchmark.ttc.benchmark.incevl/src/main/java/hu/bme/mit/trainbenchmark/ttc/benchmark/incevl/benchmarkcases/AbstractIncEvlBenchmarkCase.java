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

import hu.bme.mit.trainbenchmark.ttc.benchmark.emf.EMFBenchmarkCase;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.evl.incremental.TraceEvlModule;

public abstract class AbstractIncEvlBenchmarkCase extends EMFBenchmarkCase {
	
	protected String filename;
	protected TraceEvlModule module;

	protected AbstractIncEvlBenchmarkCase(String filename) {
		this.filename = filename;
	}

	@Override
	protected void init() throws IOException {
		super.init();
		module = new TraceEvlModule();
		try {
			module.parse(this.getClass().getResource("/evl/" + filename).toURI());
			module.getContext().getTrace();
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
			boolean hasTrace = module.getContext().hasTrace();
			module.execute();
			if (!hasTrace) {
				module.getContext().attachChangeListeners();
			}
			return this.processCheck(module.getContext().getUnsatisfiedConstraints());
		} catch(EolRuntimeException e) {
			throw new IOException(e);
		}	
	}

	@Override
	public void read() throws IOException {
		super.read();
		InMemoryEmfModel model = new InMemoryEmfModel(resource);
		this.module.getContext().getModelRepository().addModel(model);
	}
	
	@Override
	protected void modify(Collection<Object> matches) {
		this.getTransform().transform(matches);
	}

	@Override
	protected void registerComparator() {
		this.comparator = new IncEvlMatchComparator();
	}
	
	protected abstract Collection<Object> processCheck(Collection<UnsatisfiedConstraint> unsatisfied);
	protected abstract AbstractIncEvlTransformationAction getTransform();
}
