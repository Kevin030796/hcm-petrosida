package org.taowi.hcm.core.validator;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.base.event.LoginEventData;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.osgi.service.event.Event;

public class HCCoreValidatorFactory extends AbstractEventHandler {
	private CLogger log = CLogger.getCLogger(HCCoreValidatorFactory.class);


	@Override
	protected void initialize() {
		registerEvent(IEventTopics.AFTER_LOGIN);

		//log.info("PROJECT MANAGEMENT EVENT MANAGER // INITIALIZED");
	}

	
	
	@Override
	protected void doHandleEvent(Event event) {
		String msg = "";
		if (event.getTopic().equals(IEventTopics.AFTER_LOGIN)) {
			LoginEventData eventData = getEventData(event);
			log.info(" topic="+event.getTopic()+" AD_Client_ID="+eventData.getAD_Client_ID()
					+" AD_Org_ID="+eventData.getAD_Org_ID()+" AD_Role_ID="+eventData.getAD_Role_ID()
					+" AD_User_ID="+eventData.getAD_User_ID());

		}
		
		
		if (msg.length() > 0)
			throw new AdempiereException(msg);

	}

	


}
