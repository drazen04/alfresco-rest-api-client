package it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

/**
 * 
 * <h1>Pair.java</h1>
 *
 * <p>
 * </p>
 *
 * @version 1.0.0
 * @since 1.0.0
 * @author Daniele Del Vecchio
 * @lastUpdate 2022-12-15 - Daniele Del Vecchio
 */
public final class Pair<F, S> implements Serializable {

	private static final long serialVersionUID = -3107968557823829523L;

	private F first;
	private S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
		GetField fields = objectInputStream.readFields();
		
		if (fields.defaulted("first")) {
			
			this.first = (F) fields.get("fFirst", null);
			this.second = (S) fields.get("fSecond", null);
			
		} else {
			
			this.first = (F) fields.get("first", null);
			this.second = (S) fields.get("second", null);
			
		}
	}

	public final F getFirst() {
		return first;
	}

	public final void setFirst(F first) {
		this.first = first;
	}

	public final S getSecond() {
		return second;
	}

	public final void setSecond(S second) {
		this.second = second;
	}

}