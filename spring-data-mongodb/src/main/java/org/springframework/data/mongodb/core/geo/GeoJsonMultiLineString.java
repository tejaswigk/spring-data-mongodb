/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core.geo;

import static java.util.Collections.*;
import static org.springframework.util.Assert.*;
import static org.springframework.util.ObjectUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Point;

/**
 * {@link GeoJsonMultiLineString} is defined as list of {@link GeoJsonLineString}s.
 * 
 * @author Christoph Strobl
 * @since 1.7
 * @see http://geojson.org/geojson-spec.html#multilinestring
 */
public class GeoJsonMultiLineString implements GeoJson<Iterable<GeoJsonLineString>> {

	private static final String TYPE = "MultiLineString";
	private List<GeoJsonLineString> coordinates = new ArrayList<GeoJsonLineString>();

	/**
	 * Creates new {@link GeoJsonMultiLineString}.
	 * 
	 * @param lines must not be {@literal null}.
	 */
	@SuppressWarnings("unchecked")
	public GeoJsonMultiLineString(List<Point>... lines) {

		notEmpty(lines, "Lines for MultiLineString must not be null!");
		for (List<Point> line : lines) {
			this.coordinates.add(new GeoJsonLineString(line));
		}
	}

	/**
	 * @param lines must not be {@literal null}.
	 */
	public GeoJsonMultiLineString(List<GeoJsonLineString> lines) {

		notNull(lines, "Lines for MultiLineString must not be null!");
		this.coordinates.addAll(lines);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mongodb.core.geo.GeoJson#getType()
	 */
	@Override
	public String getType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mongodb.core.geo.GeoJson#getCoordinates()
	 */
	@Override
	public Iterable<GeoJsonLineString> getCoordinates() {
		return unmodifiableList(this.coordinates);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return nullSafeHashCode(this.coordinates);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GeoJsonMultiLineString)) {
			return false;
		}
		return nullSafeEquals(this.coordinates, ((GeoJsonMultiLineString) obj).coordinates);
	}

}
