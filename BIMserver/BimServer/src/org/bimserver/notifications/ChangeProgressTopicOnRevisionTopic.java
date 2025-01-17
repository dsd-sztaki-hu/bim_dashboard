package org.bimserver.notifications;

/******************************************************************************
 * Copyright (C) 2009-2019  BIMserver.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see {@literal<http://www.gnu.org/licenses/>}.
 *****************************************************************************/

import org.bimserver.BimserverDatabaseException;
import org.bimserver.endpoints.EndPoint;
import org.bimserver.shared.exceptions.ServerException;
import org.bimserver.shared.exceptions.UserException;

public class ChangeProgressTopicOnRevisionTopic extends Topic {

	private ChangeProgressTopicOnRevisionTopicKey key;

	public ChangeProgressTopicOnRevisionTopic(NotificationsManager notificationsManager, ChangeProgressTopicOnRevisionTopicKey key) {
		super(notificationsManager);
		this.key = key;
	}

	public void notifyOfNewTopic(final NewProgressTopicOnRevisionNotification notification) throws UserException, ServerException, BimserverDatabaseException {
		map(new Mapper(){
			@Override
			public void map(final EndPoint endPoint) throws UserException, ServerException {
				endPoint.getNotificationInterface().newProgressOnRevisionTopic(notification.getPoid(), notification.getRoid(), notification.getTopicId());
			}
		});
	}

	@Override
	public void remove() {
		getNotificationsManager().removeChangeProgressTopicOnRevision(key);
	}
}
