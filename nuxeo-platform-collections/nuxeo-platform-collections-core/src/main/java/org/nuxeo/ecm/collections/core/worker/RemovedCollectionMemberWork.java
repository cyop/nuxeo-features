/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     <a href="mailto:grenard@nuxeo.com">Guillaume</a>
 */
package org.nuxeo.ecm.collections.core.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.collections.core.adapter.Collection;
import org.nuxeo.ecm.collections.core.listener.CollectionAsynchrnonousQuery;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;

/**
 * @since 5.9.3
 */
public class RemovedCollectionMemberWork extends RemovedAbstractWork {

    public RemovedCollectionMemberWork() {
        super();
    }

    protected RemovedCollectionMemberWork(long offset) {
        super(offset);
    }

    private static final Log log = LogFactory.getLog(RemovedCollectionMemberWork.class);

    private static final long serialVersionUID = 6944563540430297431L;

    public static final String CATEGORY = "removedCollectionMember";

    protected static final String TITLE = "Remove CollectionMember Work";

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    protected String getQuery() {
        return CollectionAsynchrnonousQuery.QUERY_FOR_COLLECTION_MEMBER_REMOVED;
    }

    @Override
    protected void updateDocument(final DocumentModel collection) throws ClientException {
        log.trace(String.format("Worker %s, updating Collection %s", getId(),
                collection.getTitle()));

        Collection collectionAdapter = collection.getAdapter(Collection.class);
        collectionAdapter.removeDocument(docId);
        session.saveDocument(collection);
    }

}
