<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");
long messageId = ParamUtil.getLong(request, "messageId");
String messageType = ParamUtil.getString(request, "messageType");
long replyMessageId = ParamUtil.getLong(request, "replyMessageId");

MailManager mailManager = MailManager.getInstance(request);

Message message = null;

String to = StringPool.BLANK;
String cc = StringPool.BLANK;
String subject = StringPool.BLANK;
String body = StringPool.BLANK;

if (messageType.equals("new")) {
	message = mailManager.addDraft(accountId);

	messageId = message.getMessageId();
}
else if (messageType.equals("edit")) {
	message = MessageLocalServiceUtil.getMessage(messageId);

	to = message.getTo();
	cc = message.getCc();
	subject = message.getSubject();
	body = message.getBody();
}
else {
	message = mailManager.addDraft(accountId);

	messageId = message.getMessageId();

	Message replyMessage = MessageLocalServiceUtil.getMessage(replyMessageId);

	if (messageType.equals("reply")) {
		to = replyMessage.getSender();
		subject = LanguageUtil.format(pageContext, "re-x", replyMessage.getSubject());
	}
	else if (messageType.equals("reply-all")) {
		to = replyMessage.getSender() + ", " + replyMessage.getTo();
		cc = replyMessage.getCc();
		subject = LanguageUtil.format(pageContext, "re-x", replyMessage.getSubject());
	}
	else if (messageType.equals("forward")) {
		subject = LanguageUtil.format(pageContext, "fwd-x", replyMessage.getSubject());
	}

	StringBundler sb = new StringBundler(4);

	sb.append("<br /><br />");
	sb.append(LanguageUtil.format(pageContext, "on-x-x-wrote", new Object[] {dateFormatDateTime.format(replyMessage.getSentDate()), replyMessage.getSender()}));
	sb.append("<br />");
	sb.append(replyMessage.getBody());

	body = sb.toString();
}
%>

<form id="<portlet:namespace />fm" method="post" name="<portlet:namespace />fm">
	<aui:input type="hidden" name="accountId" value="<%= accountId %>" />
	<aui:input type="hidden" name="messageId" value="<%= messageId %>" />

	<aui:input name="to" value="<%= to %>" />

	<aui:input name="cc" value="<%= cc %>" />

	<aui:input name="bcc" />

	<aui:input name="subject" value="<%= subject %>" />

	<aui:field-wrapper label="body">
		<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" toolbarSet="email" width="100%" />

		<aui:input name="body" type="hidden" />
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button type="submit" value="send" />

		<aui:button cssClass="save-draft" data-messageId="<%= messageId %>" type="button" value="save" />

		<aui:button cssClass="discard-draft" data-messageId="<%= messageId %>" type="button" value="discard" />
	</aui:button-row>
</form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(body) %>";
	}
</aui:script>

<aui:script use="aui-io">
	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			event.preventDefault();

			document.<portlet:namespace />fm.<portlet:namespace />body.value = window.<portlet:namespace />editor.getHTML();

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/send_message',
				{
					dataType: 'json',
					form: {
						id: form.getDOM()
					},
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
						},
						success: function(event, id, obj) {
							var results = this.get('responseData');

							Liferay.Mail.setStatus(results.status, results.message);

							if (results.status == 'success') {
								Liferay.Mail.loadMessages(Liferay.Mail.folderId, Liferay.Mail.pageNumber, Liferay.Mail.orderByField, Liferay.Mail.orderByType, Liferay.Mail.keywords);
							}
						}
					}
				}
			);
		}
	);

	form.one('.save-draft').on(
		'click',
		function(event) {
			document.<portlet:namespace />fm.<portlet:namespace />body.value = window.<portlet:namespace />editor.getHTML();

			A.io.request(
				themeDisplay.getLayoutURL() + '/-/mail/save_draft',
				{
					dataType: 'json',
					form: {
						id: form.getDOM()
					},
					on: {
						failure: function(event, id, obj) {
							Liferay.Mail.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
						},
						success: function(event, id, obj) {
							var results = this.get('responseData');

							Liferay.Mail.setStatus(results.status, results.message);
						}
					}
				}
			);
		}
	);
</aui:script>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.default";
%>