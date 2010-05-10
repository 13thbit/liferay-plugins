AUI().add(
	'liferay-soffice-invitemembers',
	function(A) {
		var InviteMembers = function() {
			InviteMembers.superclass.constructor.apply(this, arguments);
		};

		InviteMembers.NAME = 'soinvitemembers';

		InviteMembers.ATTRS = {
			portletNamespace: {
				value: ''
			}
		};

		A.extend(
			InviteMembers,
			A.Base,
			{
				initializer: function(params) {
					var instance = this;

					instance._inviteMembersWrapper = A.one('#so-invitemembers-wrapper');

					if (!instance._inviteMembersWrapper) {
						return;
					}

					instance._findMembersList = instance._inviteMembersWrapper.one('.search .list');
					instance._emailInput = instance._inviteMembersWrapper.one('#new-member-email-address');
					instance._invitedEmailList = instance._inviteMembersWrapper.one('.email-invited .list');
					instance._invitedMembersList = instance._inviteMembersWrapper.one('.user-invited .list');

					A.one('.so-portlet-members .invite-members a').on(
						'click',
						function(event) {
							instance._showInviteMembers();
						}
					);

					instance._inviteMembersWrapper.delegate(
						'click',
						function(event) {
							var user = event.currentTarget;

							var userEmail = user.attr('data-emailAddress');
							var userId = user.attr('data-userId');

							if (userId) {
								if (user.hasClass('invited')) {
									instance._removeMemberInvite(user, userId);
								}
								else {
									instance._addMemberInvite(user);
								}
							}
							else {
								instance._removeEmailInvite(user);
							}
						},
						'.user'
					);

					instance._inviteMembersWrapper.delegate(
						'keyup',
						function(event) {
							if (event.keyCode == 13) {
								instance._addMemberEmail();
							}
						},
						'.controls'
					);

					instance._inviteMembersWrapper.delegate(
						'click',
						function(event) {
							instance._addMemberEmail();

							Liferay.Util.focusFormField(instance._emailInput.getDOM());
						},
						'#so-add-email-address'
					);

					instance._bindForm();

					new A.LiveSearch(
						{
							input: '#invite-user-search',
							nodes: '#so-invitemembers-wrapper .search .user'
						}
					);
				},

				_addMemberEmail: function() {
					var instance = this;

					var emailAddress = A.Lang.trim(instance._emailInput.val());

					if (emailAddress) {
						var html = '<div class="user" data-emailAddress="' + emailAddress + '"><span class="email">' + emailAddress + '</span></div>';

						instance._invitedEmailList.append(html);
					}

					instance._emailInput.val('');
				},

				_addMemberInvite: function(user) {
					var instance = this;

					user.addClass('invited').cloneNode(true).appendTo(instance._invitedMembersList);
				},

				_bindForm: function() {
					var instance = this;

					var form = instance._inviteMembersWrapper.one('form');

					form.on(
						'submit',
						function(event) {
							event.preventDefault();

							instance._syncFields(form);

							if (!instance._inviteMembersWrapper.io) {
								instance._inviteMembersWrapper.plug(
									A.Plugin.IO,
									{
										autoLoad: false,
										form: {id: form.getDOM()},
										method: 'POST',
										on: {success: A.bind(instance._bindForm, instance)},
										uri: form.getAttribute('action')
									}
								);
							}

							instance._inviteMembersWrapper.io.start();
						}
					);
				},

				_removeEmailInvite: function(user) {
					user.remove();
				},

				_removeMemberInvite: function(user, userId) {
					var instance = this;

					userId = userId || user.getAttribute('data-userId');

					var user = instance._findMembersList.one('[data-userId="' + userId + '"]');
					var invitedUser = instance._invitedMembersList.one('[data-userId="' + userId + '"]');

					user.removeClass('invited');
					invitedUser.remove();
				},

				_showInviteMembers: function() {
					var instance = this;

					var profile = A.one('#so-profile-wrapper');

					if (profile) {
						profile.hide();
					}

					instance._inviteMembersWrapper.show();
				},

				_syncFields: function(form) {
					var instance = this;

					var userIds = [];
					var emailAddresses = [];

					instance._invitedMembersList.all('.user').each(
						function(user, index, collection) {
							userIds.push(user.attr('data-userId'));
						}
					);

					instance._invitedEmailList.all('.user').each(
						function(emailAddress, index, collection) {
							emailAddresses.push(emailAddress.attr('data-emailAddress'));
						}
					);

					var teamId = form.one('select[name="' + instance.get('portletNamespace') + 'teamId"]') || '0';

					form.one('input[name="' + instance.get('portletNamespace') + 'receiverUserIds"]').val(userIds.join());
					form.one('input[name="' + instance.get('portletNamespace') + 'receiverEmailAddresses"]').val(emailAddresses.join());
					form.one('input[name="' + instance.get('portletNamespace') + 'invitedTeamId"]').val(teamId);
				}
			}
		);

		Liferay.namespace('SO');

		Liferay.SO.InviteMembers = InviteMembers;
	},
	'',
	{
		requires: ['aui-base', 'aui-live-search']
	}
);