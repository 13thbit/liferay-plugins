AUI().use(
	'aui-base', 'aui-dialog', 'aui-io-plugin',
	function(A) {
		LiferayInc = {
			init: function() {
				var instance = this;

				instance.handleAddContent();
				instance.handleMySitesDropDown();
			},

			handleAddContent: function() {
				var instance = this;

				var addContent = A.one('#soAddContent');

				if (!addContent) {
					return;
				}

				addContent.on(
					'click',
					function() {
						if (!instance._addContentDialog) {
							instance._addContentDialog = new A.Dialog(
								{
									title: Liferay.Language.get('add-content'),
									width: 250,
									xy: [15,15]
								}
							).plug(
								A.Plugin.IO,
								{
									data: {
										doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
										p_l_id: themeDisplay.getPlid(),
										p_p_id: 87,
										p_p_state: 'exclusive'
									},
									on: {
										success: function() {
											AUI().use(
												'liferay-layout-configuration',
												function(A) {
													Liferay.LayoutConfiguration._dialogBody = instance._addContentDialog.bodyNode;

													Liferay.LayoutConfiguration._loadContent();
												}
											);
										}
									},
									uri: themeDisplay.getPathMain() + '/portal/render_portlet'
								}
							).render();
						}

						instance._addContentDialog.show();
					}
				);
			},

			handleMySitesDropDown: function() {
				$('#navigation-top .my-sites').hoverIntent(
					{
						interval: 0,
						timeout: 500,
						over: function() {
							$(this).addClass('open');
							$('.child-menu', $(this)).show();
						},
						out: function() {
							$(this).removeClass('open');
							$('.child-menu', $(this)).hide();
						}
					}
				);
			}
		};
	}
);

AUI().ready(
	function(A) {
		LiferayInc.init();
	}
);
