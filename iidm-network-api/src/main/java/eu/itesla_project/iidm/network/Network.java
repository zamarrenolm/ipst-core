/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package eu.itesla_project.iidm.network;

import java.util.Collection;
import java.util.Set;
import org.joda.time.DateTime;

/**
 * A power network model.
 *
 * <p>To create a new network, use {@link NetworkFactory}.
 *
 * <p>The network is initially created with one state identified by
 * <code>StateManager.INITIAL_STATE_ID</code>. {@link StateManager} is
 * responsible for state management and is accessible from the network thanks
 * to {@link #getStateManager()}.
 *
 * <p>Instances of <code>Network</code> are not thread safe except for attributes
 * depending of the state (always specified in the javadoc) if
 * {@link StateManager#allowStateMultiThreadAccess(boolean)} is set to true.
 *
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 * @see NetworkFactory
 * @see StateManager
 */
public interface Network extends Container<Network> {

    /**
     * A global bus/breaker view of the network.
     * <p>
     * Depends on the working state.
     * @see StateManager
     */
    public static interface BusBreakerView {

        /**
         * Get all buses.
         * <p>
         * Depends on the working state.
         * @see StateManager
         */
        Iterable<Bus> getBuses();

        /**
         * Get all switches.
         */
        Iterable<Switch> getSwitchs();

    }

    /**
     * A global bus view of the network.
     */
    public static interface BusView {

        /**
         * Get all buses.
         * <p>
         * Depends on the working state.
         * @see StateManager
         */
        Iterable<Bus> getBuses();

        /**
         * Get all connected compoments.
         * <p>
         * Depends on the working state.
         * @see StateManager
         */
        Collection<ConnectedComponent> getConnectedComponents();

    }

    /**
     * Get the date that the network represents.
     */
    DateTime getCaseDate();

    /**
     * Set the date that the network represents.
     * @throws IllegalArgumentException if date is null.
     */
    Network setCaseDate(DateTime date);

    /**
     * Get the forecast distance in minutes.
     * <p>Example: 0 for a snapshot, 6*60 to 30*60 for a DACF.
     */
    int getForecastDistance();

    Network setForecastDistance(int forecastDistance);

    /**
     * Get the source format.
     * @return the source format
     */
    String getSourceFormat();

    /**
     * Get the state manager of the network.
     */
    StateManager getStateManager();

    /**
     * Get all countries.
     */
    Set<Country> getCountries();

    /**
     * Get the country count.
     */
    int getCountryCount();

    /**
     * Get a builder to create a new substation.
     */
    SubstationAdder newSubstation();

    /**
     * Get all substations.
     */
    Iterable<Substation> getSubstations();

    /**
     * Get the substation count.
     */
    int getSubstationCount();

    /**
     * Get substation located in a specific county, TSO and marked with a list
     * of geographical tag.
     *
     * @param country the country, if <code>null</code> there is no
     *                  filtering on countries
     * @param tsoId the id of the TSO, if <code>null</code> there is no
     *                  filtering on TSOs
     * @param geographicalTags a list a geographical tags
     */
    Iterable<Substation> getSubstations(Country country, String tsoId, String... geographicalTags);

    /**
     * Get a substation.
     *
     * @param id the id of the substation
     */
    Substation getSubstation(String id);

    /**
     * Get all substation voltage levels.
     */
    Iterable<VoltageLevel> getVoltageLevels();

    /**
     * Get the voltage level count.
     */
    int getVoltageLevelCount();

    /**
     * Get a substation voltage level.
     *
     * @param id the id of the substation voltage level
     */
    VoltageLevel getVoltageLevel(String id);

    /**
     * Get a builder to create a new AC line.
     */
    LineAdder newLine();

    /**
     * Get all AC lines.
     */
    Iterable<Line> getLines();

    /**
     * Get the AC line count.
     */
    int getLineCount();

    /**
     * Get a AC line.
     *
     * @param id the name of the AC line
     */
    Line getLine(String id);

    /**
     * Get a builder to create a new AC tie line.
     */
    TieLineAdder newTieLine();

    /**
     * Get all two windings transformers.
     */
    Iterable<TwoWindingsTransformer> getTwoWindingsTransformers();

    /**
     * Get the two windings transformer count.
     */
    int getTwoWindingsTransformerCount();

    /**
     * Get a two windings transformer.
     *
     * @param id the id of the two windings transformer
     */
    TwoWindingsTransformer getTwoWindingsTransformer(String id);

    /**
     * Get all 3 windings transformers.
     */
    Iterable<ThreeWindingsTransformer> getThreeWindingsTransformers();

    /**
     * Get the 3 windings transformer count.
     */
    int getThreeWindingsTransformerCount();

    /**
     * Get a 3 windings transformer.
     *
     * @param id the id of the 3 windings transformer
     */
    ThreeWindingsTransformer getThreeWindingsTransformer(String id);

    /**
     * Get all generators.
     */
    Iterable<Generator> getGenerators();

    /**
     * Get the generator count.
     */
    int getGeneratorCount();

    /**
     * Get a generator.
     *
     * @param id the id od the generator
     */
    Generator getGenerator(String id);

    /**
     * Get all loads.
     */
    Iterable<Load> getLoads();

    /**
     * Get the load count.
     */
    int getLoadCount();

    /**
     * Get a load.
     *
     * @param id the id the load
     */
    Load getLoad(String id);

    /**
     * Get all compensator shunts.
     */
    Iterable<ShuntCompensator> getShunts();

    /**
     * Get the shunt count.
     */
    int getShuntCount();

    /**
     * Get a compensator shunt.
     *
     * @param id the id of the compensator shunt
     */
    ShuntCompensator getShunt(String id);

    /**
     * Get all dangling lines.
     */
    Iterable<DanglingLine> getDanglingLines();

    /**
     * Get the dangling line count.
     */
    int getDanglingLineCount();

    /**
     * Get a dangling line.
     *
     * @param id the id of the dangling line
     */
    DanglingLine getDanglingLine(String id);

    /**
     * Get all static var compensators.
     */
    Iterable<StaticVarCompensator> getStaticVarCompensators();

    /**
     * Get the static var compensator count.
     */
    int getStaticVarCompensatorCount();

    /**
     * Get a static var compensator.
     *
     * @param id the id of the static var compensator
     */
    StaticVarCompensator getStaticVarCompensator(String id);

    /**
     * Get a switch from its id.
     * @param id id of the switch
     * @return the switch
     */
    Switch getSwitch(String id);

    /**
     * Get all HVDC converter stations.
     * @return all HVDC converter stations
     */
    Iterable<HvdcConverterStation<?>> getHvdcConverterStations();

    /**
     * Get HVDC converter stations count.
     * @return HVDC converter station count
     */
    int getHvdcConverterStationCount();

    /**
     * Get an HVDC converter station.
     * @param id the id of the HVDC converter station
     * @return the HVDC converter station or null if not found
     */
    HvdcConverterStation<?> getHvdcConverterStation(String id);

    /**
     * Get all HVDC lines.
     * @return all HVDC lines
     */
    Iterable<HvdcLine> getHvdcLines();

    /**
     * Get HVDC lines count.
     * @return HVDC lines count
     */
    int getHvdcLineCount();

    /**
     * Get an HVDC line.
     * @param id the id of the HVDC line
     * @return the HVDC line or null if not found
     */
    HvdcLine getHvdcLine(String id);

    /**
     * Get a builder to create a new HVDC line.
     * @return a builder to create a new HVDC line
     */
    HvdcLineAdder newHvdcLine();

    /**
     * Get a equipment.
     *
     * @param id the id of the equipment
     */
    Identifiable<?> getIdentifiable(String id);

    /**
     * Get all identifiables of the network.
     *
     * @return all identifiables of the network
     */
    Collection<Identifiable<?>> getIdentifiables();

    /**
     * Get a bus/breaker view of the network.
     */
    BusBreakerView getBusBreakerView();

    /**
     * Get a bus view of the network.
     */
    BusView getBusView();

    /**
     * Merge with an other network. At the end of the merge the other network
     * is empty.
     * @param other the other network
     */
    void merge(Network other);

    void merge(Network... others);

    void addListener(NetworkListener listener);

    void removeListener(NetworkListener listener);
}
