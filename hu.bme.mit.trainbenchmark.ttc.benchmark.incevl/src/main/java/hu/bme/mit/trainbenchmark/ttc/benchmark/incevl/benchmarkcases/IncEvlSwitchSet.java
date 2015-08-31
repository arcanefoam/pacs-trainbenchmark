package hu.bme.mit.trainbenchmark.ttc.benchmark.incevl.benchmarkcases;

import hu.bme.mit.trainbenchmark.ttc.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.ttc.railway.Route;
import hu.bme.mit.trainbenchmark.ttc.railway.Semaphore;
import hu.bme.mit.trainbenchmark.ttc.railway.Switch;
import hu.bme.mit.trainbenchmark.ttc.railway.SwitchPosition;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;

public class IncEvlSwitchSet extends AbstractIncEvlBenchmarkCase {

	public IncEvlSwitchSet() {
		super("SwitchSet.evl");
	}
	
	@Override
	protected Collection<Object> processCheck(
			Collection<UnsatisfiedConstraint> unsatisfied) {
		this.matches = new HashSet<Object>();
		
		for (UnsatisfiedConstraint uc : unsatisfied) {
			if (!(uc.getInstance() instanceof Route) 
					|| uc.getExtras().isEmpty()
					|| !uc.getConstraint().getName().equals("SwitchSet")) {
				continue;
			}
			for (Object tupleObj : (List<Object>) uc.getExtras().get("matches")) {
				Map<String, Object> tuple = (Map<String, Object>) tupleObj;
				this.matches.add(
						new Match(
								(Semaphore) tuple.get("semaphore"), 
								(Route) tuple.get("route"), 
								(SwitchPosition) tuple.get("switchPosition"), 
								(Switch) tuple.get("switch")));
			}
		}
		
		return this.matches;
	}

	@Override
	protected AbstractIncEvlTransformationAction getTransform() {
		return new Transformation();
	}

	/**
	 * {@link AbstractIncEvlTransformationAction} implementation for
	 * {@link IncEvlSwitchSet} case.
	 * 
	 * @author Jonathan Co
	 *
	 */
	public static class Transformation extends AbstractIncEvlTransformationAction {

		@Override
		public void transform(Collection<Object> matches) {
			for (final Object match : matches) {
				final Match ssm = (Match) match;
				ssm.getSw().setCurrentPosition(ssm.getSwitchPosition().getPosition());
			}
		}
	}

	/**
	 * {@link AbstractIncEvlMatch} implementation for {@link IncEvlSwitchSet} case.
	 * 
	 * @author Jonathan Co
	 *
	 */
	public class Match extends AbstractIncEvlMatch {

		public Match(final Semaphore semaphore, final Route route,
				final SwitchPosition switchPosition, final Switch sw) {
			super();
			match = new RailwayElement[] { semaphore, route, switchPosition, sw };
		}

		public Semaphore getSemaphore() {
			return (Semaphore) match[0];
		}

		public Route getRoute() {
			return (Route) match[1];
		}

		public SwitchPosition getSwitchPosition() {
			return (SwitchPosition) match[2];
		}

		public Switch getSw() {
			return (Switch) match[3];
		}
	}



}
