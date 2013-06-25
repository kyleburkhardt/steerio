
package org.magnos.steer.spatial;

import org.magnos.steer.Vector;


/**
 * A data structure which stores entities that exist in 2d space for querying
 * and collision handling.
 */
public interface SpatialDatabase
{

	public static final long ALL_GROUPS = 0xFFFFFFFFFFFFFFFFL;
	
	public static int MAX_RESULTS = 16;

	/**
	 * Adds an entity to the database.
	 */
	public void add( SpatialEntity entity );

	/**
	 * Removes all inert entities and updates the internal structure of the
	 * database to match the new positions of the non-static entities.
	 * 
	 * @return The number of active entities in the database.
	 */
	public int refresh();

	/**
	 * Notifies the given callback of a maximum number of entities that are in
	 * the given collision groups and intersect with the given circle.
	 * 
	 * @param offset
	 *        The offset of the intersection query.
	 * @param radius
	 *        The radius of the intersection query.
	 * @param max
	 *        The maximum number of entities to check.
	 * @param collidesWith
	 *        If an entity does not exist in any of the collision groups
	 *        specified, it's ignored.
	 * @param callback
	 *        The callback to notify when an entity intersects with the search
	 *        query.
	 * @return The number of intersections accepted.
	 */
	public int intersects( Vector offset, float radius, int max, long collidesWith, SearchCallback callback );

	/**
	 * Notifies the given callback of a maximum number of entities that are in
	 * the given collision groups and are contained in the given circle.
	 * 
	 * @param offset
	 *        The offset of the containment query.
	 * @param radius
	 *        The radius of the containment query.
	 * @param max
	 *        The maximum number of entities to check.
	 * @param collidesWith
	 *        If an entity does not exist in any of the collision groups
	 *        specified, it's ignored.
	 * @param callback
	 *        The callback to notify when an entity is contained in the search
	 *        query.
	 * @return The number of containments accepted.
	 */
	public int contains( Vector offset, float radius, int max, long collidesWith, SearchCallback callback );

	/**
	 * Performs a K-nearest-neighbor search around the given point on entities
	 * that are in the given collision groups. The results are placed in arrays
	 * that are passed in where the first entries are the closest entities and
	 * their distance (squared) and the last entries are the farthest entities
	 * and their distance.
	 * 
	 * @param point
	 *        The point of the KNN query.
	 * @param k
	 *        The maximum number of neighbors to return.
	 * @param collidesWith
	 *        If an entity does not exist in any of the collision groups
	 *        specified, it's ignored.
	 * @param nearest
	 *        The array of entities nearest to the given point.
	 * @param distance
	 *        The array of distances between the given point and nearest
	 *        entities.
	 * @return The number of neighbors found. If the output arrays are not big
	 *         enough then zero is returned immediately.
	 */
	public int knn( Vector point, int k, long collidesWith, SpatialEntity[] nearest, float[] distance );

}
