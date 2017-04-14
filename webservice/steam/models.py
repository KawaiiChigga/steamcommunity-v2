# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from __future__ import unicode_literals

from django.db import models


class Discussion(models.Model):
    discussionid = models.AutoField(db_column='discussionID', primary_key=True)  # Field name made lowercase.
    gamename = models.CharField(max_length=50)
    description = models.TextField(blank=True, null=True)
    imgurl = models.CharField(max_length=256)

    class Meta:
        db_table = 'discussion'


class Friends(models.Model):
    fid = models.AutoField(primary_key=True)
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='userID', related_name="user_id")  # Field name made lowercase.
    friendid = models.ForeignKey('User', models.DO_NOTHING, db_column='friendID', related_name="friend_id")  # Field name made lowercase.
    status = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'friends'


class Post(models.Model):
    postid = models.AutoField(db_column='postID', primary_key=True)  # Field name made lowercase.
    threadid = models.ForeignKey('Thread', models.DO_NOTHING, db_column='threadID')  # Field name made lowercase.
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='userID')  # Field name made lowercase.
    message = models.TextField()
    postdatetime = models.DateTimeField(db_column='postDateTime')  # Field name made lowercase.
    updatedatetime = models.DateTimeField(db_column='updateDateTime')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'post'


class Thread(models.Model):
    threadid = models.AutoField(db_column='threadID', primary_key=True)  # Field name made lowercase.
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='userID')  # Field name made lowercase.
    discussionid = models.ForeignKey(Discussion, models.DO_NOTHING, db_column='discussionID')  # Field name made lowercase.
    title = models.CharField(max_length=50)
    publishdatetime = models.DateTimeField(db_column='publishDateTime')  # Field name made lowercase.
    ispinned = models.IntegerField(db_column='isPinned')  # Field name made lowercase.
    categorytype = models.IntegerField(db_column='categoryType')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'thread'


class User(models.Model):
    userid = models.AutoField(db_column='userID', primary_key=True)  # Field name made lowercase.
    username = models.CharField(max_length=30)
    password = models.CharField(max_length=100)
    email = models.CharField(max_length=30)
    imageurl = models.CharField(db_column='imageURL', max_length=256, blank=True, null=True)  # Field name made lowercase.
    description = models.CharField(max_length=256, blank=True, null=True)
    name = models.CharField(max_length=100, blank=True, null=True)
    country = models.CharField(max_length=100, blank=True, null=True)
    province = models.CharField(max_length=100, blank=True, null=True)
    city = models.CharField(max_length=100, blank=True, null=True)
    joindate = models.DateTimeField(db_column='joinDate')  # Field name made lowercase.
    discussionid = models.ForeignKey(Discussion, models.DO_NOTHING, db_column='discussionID', blank=True, null=True)  # Field name made lowercase.
    class Meta:
        managed = False
        db_table = 'user'
