Unit 8: Group Milestone - README
===

# RattlerConnect

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
 An application where FAMU alumni and students can network. 

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Social Networking 
- **Mobile:** This application would be primarily developed for mobile but can be viable on a computer, such as Instagram,LinkedIn,and GroupMe. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Analyzes users profession or major, and connects them to other users in the same field. The user can then decide to chat with this person,send their resume, or befriend them.  
- **Market:** Any former or current student of Florida Agricultural and Mechanical University can utilize this app.
- **Habit:** students are using the app daily to check the status of upcoming job and internship opportunities. Alumni are using the app frequently to post new job opportunities and seek out new employees/ interns. 
- **Scope:** First we would start with pairing people based on their field of work, then perhaps this could evolve into big companies networking with FAMU alumni and students as well to broaden its usage.There is a large potential for the application to be used by all HBCU's so that major companies can network with users having this background.  


## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logs in to access previous chats,profile settings and view posts
* User picks their current or upcoming career field.
* User can view other users posts and profiles that are in the same career field as themselves (Think Instagram).
* User can send their resume under a post if they are interested in the position (Think LikedIn) 
* Users would be able to create/ join chatrooms to network with other users(GroupMe Style).
* Profile pages for each user.
* Settings (Accesibility, Notification, General, etc.)
* User can like a photo
* User can follow/unfollow another user

**Optional Nice-to-have Stories**

* User would be able to advertise their business to gain clients
* Users would be able to create stories (think instagram, snapchat, and facebook).
* User can add a comment to a photo
* User can see notifications when their photo is liked or they are followed




### 2. Screen Archetypes

* Login
    * User can login
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with others. 
* Stream
    * User can view a feed of photos
    * User can double tap a photo to like 
   * User can upload a photo and description either together or individually
   * Allows user to see who interacts with their posts, if another user adds them to a groupchat or direct message, a user follows them,or if their resume has been viewed by another user.
   
* Messaging Screen - Chat for users to network (direct 1-on-1, and groupchats)
   * Allows users to chat with other users indivually or in a group, based on their interests
   * Users can either start or join a groupchat 
   * 
* Creation
    * User can post a new photo to their feed

* Profile Screen 
   * Allows users to view the posts and images they upload
   * Allows user to display their resume
   * Lets people change language, and app notification settings








### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Stream (Home)
* Messaging
* Creation (Camera)
* Profile


**Flow Navigation** (Screen to Screen)


* Forced Log-in
    * Home
   
* Home
    * nonification screen to view activity
    
* Messaging
    * Create a group screen
    * Join a group screen

* Create (Camera)
    * Add photo from gallery
    * Add document 
    * Create a poll
    * Take a photo
    * Home (after you finish posting the photo)
  
* Profile 
    * Settings  




## Wireframes

<img src = "https://i.imgur.com/dKqzGRr.jpg" width=800>

### [BONUS] Digital Wireframes & Mockups
<img src = "https://i.imgur.com/zIQ0TgC.png" width=200> <img src = "https://i.imgur.com/isflb9P.png" width=200> <img src = "https://i.imgur.com/JwyC21r.png" width=200> <img src = "https://i.imgur.com/uRYeqPI.png" width=200> <img src = "https://i.imgur.com/T9D10zQ.png" width=200> <img src = "https://i.imgur.com/ZjlwwOo.png" width=200> <img src = "https://i.imgur.com/M9qEDSv.png" width=200> <img src = "https://i.imgur.com/uJcek7P.png" width=200> <img src = "https://i.imgur.com/byKSZsI.png" width=200> <img src = "https://i.imgur.com/oFad3JD.png" width=200> <img src = "https://i.imgur.com/kt1h6Bh.png" width=200>

### [BONUS] Interactive Prototype

<img src = "https://i.imgur.com/oL3huYz.gif" width=400>


## Schema 
### Models
#### POST
| PROPERTY | TYPE | DESCRIPTION |
| -------- | ---- | ----------- |
| objectId | String | Unique id for a post |
| Author | Pointer | Author of post |
| Image | File | Image posted by the user |
| Video | File | Video posted by the user |
| Document | File | File posted by the user |
| Header/Caption | String | Header/Caption for post |
| replyCount | Number | Number of comments on a post |
| createdAt | Date/Time | Date and or time since post |
| updatedAt | Date/Time | Date and or time of last update to post |
| Poll | String and Number | Poll for displaying user choices on a post. |


#### Group Chat
| PROPERTY | TYPE | DESCRIPTION |
| -------- | ---- | ----------- |
| ObjectID | String | Unique id for a message |
| Author | Pointer | Pointer of the message sender |
| Image | File | Image posted as message |
| Video | File | Video posted as message |
| Document | File | Document posted as message |
| CreatedAt | Date/Time | Date and or time since message sent |

#### Profile
| PROPERTY | TYPE | DESCRIPTION |
| -------- | ---- | ----------- |
| ProfileID | String | Unique id for Profile |
| Name | String | Name of Person |
| Image | File | Profile Image |
| PhotoLibrary | Files | Grouping of photos |
| DocumentLibrary | Files | Grouping of documents |
| RecentPostLibrary | Pointer | Points to recent posts |
### Networking
#### List of network requests by screen

* Home
    * (Read/GET) All posts from users followers
    * (Create/POST) Create a new like on a post
    * (Create/POST) Create a new comment on a post
    * (Delete) Delete existing comment

* Nonification
    * (GET) Notifications interactons from users friends
    
* Messaging
    * (Create/CHAT) Create a new direct or group chat
    * (Delete) Delete Chat

* Create 
    * (Create/POST) Create a new post object
  
* Profile 
    * (Read/GET) Query logged in user object
    * (Read/GET) Query all posts where user is author
      ```
               ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts");
                    return;
                }
       ```
    * (Update/PUT) Update user profile image
    * (Update/PUT) Update user profile description

## Sprint 1
### Post
#### User Post
![Screen Shot 2021-03-23 at 10 24 31 PM](https://user-images.githubusercontent.com/57050918/112245138-b51aa400-8c26-11eb-9aa9-2a9994daf95b.png)
#### Recycler View for Posts
![Screen Shot 2021-03-23 at 10 28 46 PM](https://user-images.githubusercontent.com/57050918/112245479-5570c880-8c27-11eb-99e5-1414f5d09f0e.png)
### Message
<img src ="https://i.imgur.com/S4Ia2Le.png" width=200> <img src = "https://i.imgur.com/C3H3Ioi.png" width=200>
### Chat
<img src ="https://i.imgur.com/4Tz8STY.png" width=200> <img src = "https://i.imgur.com/C3H3Ioi.png" width=200>

## SignUp/Login 
<img src ="https://imgur.com/56YVBxi.png" width=200><img src ="https://imgur.com/56YVBxi.png" width=200>







