
create table Twitter_Feed (
	feedId LONG not null primary key,
	twitterUserId LONG,
	twitterScreenName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	lastStatusId LONG
);