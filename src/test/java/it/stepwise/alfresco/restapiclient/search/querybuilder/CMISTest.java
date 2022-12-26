package it.stepwise.alfresco.restapiclient.search.querybuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.CMISCondition;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.entity.DBComp;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Operator;
import it.stepwise.alfresco.restapiclient.search.querybuilder.cmis.enums.Type;

class CMISTest {

	@Test
	public void t1_checkQuery() {
		CMIS cmisQuery = CMIS.withType(Type.CMIS_ALFRESCO);
                
        String query = cmisQuery
        		.SELECT(new DBComp("colonna").aka("cl"))
        		.FROM(new DBComp("table"))
        		.WHERE(new CMISCondition("colonnaX", "213", Operator.EQUALS).fromTable("ll"))
        		.buildQuery();

		assertEquals("SELECT colonna as cl FROM table WHERE ll.colonnaX = '213'", query);
	}

	@Test
	public void t2_checkQueryJoinObjectID() {
		CMIS cmisQuery = CMIS.withType(Type.CMIS_ALFRESCO);
                
        String query = cmisQuery
				.SELECT(new DBComp("colonna").aka("cl"))
				.FROM(new DBComp("table"))
				.JOIN_OBJECTID(new DBComp("j"))
				.WHERE(new CMISCondition("colonnaX", "213", Operator.EQUALS).fromTable("ll"))
				.buildQuery();

		assertEquals("SELECT colonna as cl FROM table JOIN j ON table.cmis:objectId = j.cmis:objectId WHERE ll.colonnaX = '213'", query);
	}

	@Test
	public void t3_checkQueryJoin() {
		CMIS cmisQuery = CMIS.withType(Type.CMIS_ALFRESCO);
                
        String query = cmisQuery
				.SELECT(new DBComp("colonna").aka("cl"))
				.FROM(new DBComp("table"))
				.JOIN(new DBComp("tJoin"), "j")
				.WHERE(new CMISCondition("colonnaX", "213", Operator.EQUALS).fromTable("ll"))
				.buildQuery();

		assertEquals("SELECT colonna as cl FROM table JOIN tJoin ON tJoin.j = table.j WHERE ll.colonnaX = '213'", query);
	}

}