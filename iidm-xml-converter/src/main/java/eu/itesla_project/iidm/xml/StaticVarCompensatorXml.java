/**
 * Copyright (c) 2016, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package eu.itesla_project.iidm.xml;

import eu.itesla_project.iidm.network.StaticVarCompensator;
import eu.itesla_project.iidm.network.StaticVarCompensatorAdder;
import eu.itesla_project.iidm.network.VoltageLevel;

import javax.xml.stream.XMLStreamException;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class StaticVarCompensatorXml extends ConnectableXml<StaticVarCompensator, StaticVarCompensatorAdder, VoltageLevel> {

    static final StaticVarCompensatorXml INSTANCE = new StaticVarCompensatorXml();

    static final String ROOT_ELEMENT_NAME = "staticVarCompensator";

    @Override
    protected String getRootElementName() {
        return ROOT_ELEMENT_NAME;
    }

    @Override
    protected boolean hasSubElements(StaticVarCompensator svc) {
        return false;
    }

    @Override
    protected void writeRootElementAttributes(StaticVarCompensator svc, VoltageLevel vl, XmlWriterContext context) throws XMLStreamException {
        XmlUtil.writeFloat("bMin", svc.getBmin(), context.getWriter());
        XmlUtil.writeFloat("bMax", svc.getBmax(), context.getWriter());
        XmlUtil.writeFloat("voltageSetPoint", svc.getVoltageSetPoint(), context.getWriter());
        XmlUtil.writeFloat("reactivePowerSetPoint", svc.getReactivePowerSetPoint(), context.getWriter());
        context.getWriter().writeAttribute("regulationMode", svc.getRegulationMode().name());
        writeNodeOrBus(null, svc.getTerminal(), context);
        writePQ(null, svc.getTerminal(), context.getWriter());
    }

    @Override
    protected void writeSubElements(StaticVarCompensator svc, VoltageLevel vl, XmlWriterContext context) throws XMLStreamException {
    }

    @Override
    protected StaticVarCompensatorAdder createAdder(VoltageLevel vl) {
        return vl.newStaticVarCompensator();
    }

    @Override
    protected StaticVarCompensator readRootElementAttributes(StaticVarCompensatorAdder adder, XmlReaderContext context) {
        float bMin = XmlUtil.readFloatAttribute(context.getReader(), "bMin");
        float bMax = XmlUtil.readFloatAttribute(context.getReader(), "bMax");
        float voltageSetPoint = XmlUtil.readOptionalFloatAttribute(context.getReader(), "voltageSetPoint");
        float reactivePowerSetPoint = XmlUtil.readOptionalFloatAttribute(context.getReader(), "reactivePowerSetPoint");
        StaticVarCompensator.RegulationMode regulationMode = StaticVarCompensator.RegulationMode.valueOf(context.getReader().getAttributeValue(null, "regulationMode"));
        adder.setBmin(bMin)
                .setBmax(bMax)
                .setVoltageSetPoint(voltageSetPoint)
                .setReactivePowerSetPoint(reactivePowerSetPoint)
                .setRegulationMode(regulationMode);
        readNodeOrBus(adder, context);
        StaticVarCompensator svc = adder.add();
        readPQ(null, svc.getTerminal(), context.getReader());
        return svc;
    }

    @Override
    protected void readSubElements(StaticVarCompensator svc, XmlReaderContext context) throws XMLStreamException {
        readUntilEndRootElement(context.getReader(), () -> super.readSubElements(svc, context));
    }
}
