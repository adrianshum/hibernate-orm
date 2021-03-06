/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2015, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.boot.model.naming;

/**
 * Pluggable strategy for applying implicit naming rules when an
 * explicit name is not given.
 * <p/>
 * NOTE: the method names here mostly favor the JPA naming (aka, secondary table rather than join)
 * <p/>
 * Methods fall into 2 main categories:<ul>
 *     <li>
 *         Table naming<ul>
 *             <li>
 *                 Entity primary table - {@link #determinePrimaryTableName}.  Used when the
 *                 primary table for an entity is not explicitly named in the metadata.  See
 *                 {@link javax.persistence.Table} for details.
 *             </li>
 *             <li>
 *                 Join table - {@link #determineJoinTableName}.  See {@link javax.persistence.JoinTable}
 *                 for details.  Join table covers basically any entity association whether in the form
 *                 of a collection of entities (one-to-many, many-to-many) or a singular entity association
 *                 (many-to-one, and occasionally one-to-one).
 *             </li>
 *             <li>
 *                 Collection table - {@link #determineCollectionTableName} - Collection table
 *                 refers to any non-entity collection (basic, component/embeddable, any).  See
 *                 {@link javax.persistence.CollectionTable} for details.
 *             </li>
 *             <li>
 *                 <i>Notice that secondary tables are not mentioned, since they must always be explicitly named</i>
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         Column naming<ul>
 *
 *         </ul>
 *     </li>
 * </ul>
 *
 * @author Steve Ebersole
 */
public interface ImplicitNamingStrategy {
	/**
	 * Determine the implicit name of an entity's primary table.
	 *
	 * @param source The source information
	 *
	 * @return The implicit table name.
	 */
	public Identifier determinePrimaryTableName(ImplicitEntityNameSource source);

	/**
	 * Determine the name of an association join table given the source naming
	 * information, when a name is not explicitly given.
	 *
	 * @param source The source information
	 *
	 * @return The implicit table name.
	 */
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source);

	/**
	 * Determine the name of an collection join table given the source naming
	 * information, when a name is not explicitly given.
	 *
	 * @param source The source information
	 *
	 * @return The implicit table name.
	 */
	public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source);


	/**
	 * Determine the implicit name for the discriminator column for the given entity
	 *
	 * @param source The source information
	 *
	 * @return The implicit discriminator column name
	 */
	public Identifier determineDiscriminatorColumnName(ImplicitDiscriminatorColumnNameSource source);

	/**
	 * Determine the implicit name for the tenant (multi-tenancy) identifier column for the given entity
	 *
	 * @param source The source information
	 *
	 * @return The determined tenant identifier column name
	 */
	public Identifier determineTenantIdColumnName(ImplicitTenantIdColumnNameSource source);

	/**
	 * Determine the implicit name for the identifier column for the given entity
	 *
	 * @param source The source information
	 *
	 * @return The determined identifier column name
	 */
	public Identifier determineIdentifierColumnName(ImplicitIdentifierColumnNameSource source);

	/**
	 * Determine the name of an attribute's column given the source naming
	 * information, when a name is not explicitly given.
	 *
	 * @param source The source information
	 *
	 * @return The implicit column name.
	 */
	public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source);

	/**
	 * Determine the column name related to {@link javax.persistence.JoinColumn}.  In
	 * {@code hbm.xml} terms, this would be a {@code <key/>} defined for a collection
	 * or the column associated with a many-to-one.
	 *
	 * @param source The source information
	 *
	 * @return The determined join column name
	 */
	public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source);

	/**
	 * Determine the column name related to {@link javax.persistence.PrimaryKeyJoinColumn}.  In
	 * {@code hbm.xml} terms, this would be a {@code <key/>} defined for a {@code <join/>}
	 * or a {@code <joined-subclass/>} (others?)
	 *
	 * @param source The source information
	 *
	 * @return The determined column name
	 */
	public Identifier determinePrimaryKeyJoinColumnName(ImplicitPrimaryKeyJoinColumnNameSource source);

	/**
	 * Determine the column name related to the discriminator portion of an ANY mapping when
	 * no explicit column name is given.
	 *
	 * @param source The source information
	 *
	 * @return The determined column name
	 */
	Identifier determineAnyDiscriminatorColumnName(ImplicitAnyDiscriminatorColumnNameSource source);

	/**
	 * Determine the column name related to the key/id portion of an ANY mapping when
	 * no explicit column name is given.
	 *
	 * @param source The source information
	 *
	 * @return The determined identifier column name
	 */
	Identifier determineAnyKeyColumnName(ImplicitAnyKeyColumnNameSource source);

	Identifier determineMapKeyColumnName(ImplicitMapKeyColumnNameSource source);

	Identifier determineListIndexColumnName(ImplicitIndexColumnNameSource source);

	Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source);

	Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source);

	Identifier determineIndexName(ImplicitIndexNameSource source);
}
