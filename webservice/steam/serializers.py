from rest_framework import serializers
from steam.models import Discussion, Friends, Post, Thread, User

class DiscussionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Discussion
        fields = ('discussionid', 'gamename', 'description', 'imgurl')

class FriendsSerializer (serializers.ModelSerializer):
    class Meta:
        model = Friends
        fields = ('userid', 'friendid', 'status')

class PostSerializer (serializers.ModelSerializer):
    class Meta:
        model = Post
        fields = ('postid', 'threadid', 'userid', 'message', 'postdatetime', 'updatedatetime')

class ThreadSerializer (serializers.ModelSerializer):
    class Meta:
        model = Thread
        fields = ('threadid', 'userid', 'discussionid', 'title', 'publishdatetime', 'ispinned', 'categorytype')

class UserSerializer (serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('userid', 'username', 'email', 'imageurl', 'description', 'name', 'country', 'province',
                  'city', 'joindate', 'discussionid')

class CreateUserSerializer (serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('password', 'username', 'email', 'joindate')