-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2017 at 10:06 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `steam`
--

-- --------------------------------------------------------

--
-- Table structure for table `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add friends', 2, 'add_friends'),
(2, 'Can change friends', 2, 'change_friends'),
(3, 'Can delete friends', 2, 'delete_friends'),
(4, 'Can add user', 3, 'add_user'),
(5, 'Can change user', 3, 'change_user'),
(6, 'Can delete user', 3, 'delete_user'),
(7, 'Can add discussion', 1, 'add_discussion'),
(8, 'Can change discussion', 1, 'change_discussion'),
(9, 'Can delete discussion', 1, 'delete_discussion'),
(10, 'Can add post', 4, 'add_post'),
(11, 'Can change post', 4, 'change_post'),
(12, 'Can delete post', 4, 'delete_post'),
(13, 'Can add thread', 5, 'add_thread'),
(14, 'Can change thread', 5, 'change_thread'),
(15, 'Can delete thread', 5, 'delete_thread'),
(16, 'Can add content type', 6, 'add_contenttype'),
(17, 'Can change content type', 6, 'change_contenttype'),
(18, 'Can delete content type', 6, 'delete_contenttype'),
(19, 'Can add user', 9, 'add_user'),
(20, 'Can change user', 9, 'change_user'),
(21, 'Can delete user', 9, 'delete_user'),
(22, 'Can add group', 8, 'add_group'),
(23, 'Can change group', 8, 'change_group'),
(24, 'Can delete group', 8, 'delete_group'),
(25, 'Can add permission', 7, 'add_permission'),
(26, 'Can change permission', 7, 'change_permission'),
(27, 'Can delete permission', 7, 'delete_permission');

-- --------------------------------------------------------

--
-- Table structure for table `auth_user`
--

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_groups`
--

CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_user_permissions`
--

CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `discussion`
--

CREATE TABLE `discussion` (
  `discussionID` int(11) NOT NULL,
  `gamename` varchar(50) NOT NULL,
  `description` longtext,
  `imgurl` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discussion`
--

INSERT INTO `discussion` (`discussionID`, `gamename`, `description`, `imgurl`) VALUES
(1, 'Dota 2', 'Dota 2 is a free-to-play multiplayer online battle arena (MOBA) video game developed and published by Valve Corporation. The game is the stand-alone sequel to Defense of the Ancients (DotA), which was a community-created mod for Blizzard Entertainment''s Warcraft III: Reign of Chaos and its expansion pack, The Frozen Throne. Dota 2 is played in matches between two teams that consist of five players, with both teams occupying their own separate base on the map. Each of the ten players independently control a powerful character, known as a "hero", that each feature unique abilities and different styles of play. During a match, a player and their team collects experience points and items for their heroes in order to fight through the opposing team''s defenses. A team wins by being the first to destroy a large structure located in the opposing team''s base, called the "Ancient".\r\n\r\nDevelopment of Dota 2 began in 2009 when IceFrog, the pseudonymous lead designer of the original Defense of the Ancients mod, was hired by Valve to create a modern sequel. Dota 2 was officially released on Steam in July 2013 for Microsoft Windows, OS X, and Linux-based personal computers, following a Windows-only open beta phase that began two years prior. Despite some criticism going towards its steep learning curve and complexity, the game was praised for its rewarding gameplay, production quality, and faithfulness to its predecessor. The game initially used the original Source game engine until it was ported over to Source 2 in 2015, making it the first game to use it. Since its release, Dota 2 has been the most played game on Steam, with peaks of over a million concurrent players. The popularity of the game has led to official merchandise being produced for it, including apparel, accessories, and toys, as well as promotional tie-ins to other games and media. The game also allows for the community to create custom game modes, maps, and cosmetics for the heroes, which are then uploaded to the Steam Workshop.\r\n\r\nDota 2 has a widespread and active competitive scene, with teams from across the world playing professionally in various dedicated leagues and tournaments. Premium Dota 2 tournaments often have prize pools totaling millions of US dollars, the highest of any eSport. The largest of them is known as The International, which is produced by Valve and held annually at the KeyArena in Seattle. Valve also sponsors smaller, but more frequently held tournaments known as the Majors, which lead up to the International every year. For larger tournaments, media coverage is done by a selection of on-site staff who provide commentary and analysis for the ongoing matches, similar to traditional sporting events. Broadcasts of professional Dota 2 matches are streamed live over the internet, and sometimes simulcast on television networks, with peak viewership numbers in the millions.', 'Dota 2.jpg'),
(2, 'Counter-Strike Global Offensive', 'Counter-Strike: Global Offensive (abbreviated as CS:GO) is a multiplayer first-person shooter video game developed by Hidden Path Entertainment and Valve Corporation. It is the fourth game in the main Counter-Strike franchise. Counter-Strike: Global Offensive was released for Microsoft Windows, OS X, Xbox 360, and PlayStation 3 in August 2012, with the Linux version being released in September 2014. It features classic content, such as revamped versions of classic maps, as well as brand new maps, characters and game modes. Cross-platform multiplayer was planned between Windows, OS X, Linux, and PlayStation 3 players, but was ultimately limited to Windows, OS X, and Linux because of the difference in update-frequency between systems', 'Counter-Strike Global Offensive.jpg'),
(3, 'Tom Clancy''s Rainbow Six Siege', 'Tom Clancy''s Rainbow Six Siege is a first-person tactical shooter video game developed by Ubisoft Montreal and published by Ubisoft. Considered as a successor to the now cancelled Tom Clancy''s Rainbow 6: Patriots, Siege puts heavy emphasis on environmental destruction and cooperation between players. Unlike previous entries in the series, the title has no campaign and only offers an online mode.[', 'Tom Clancys Rainbow Six Siege.jpg'),
(4, 'Resident Evil 5', 'Resident Evil 5, known in Japan as Biohazard 5,[5] is a third-person shooter video game developed and published by Capcom. The game is the seventh major installment in the Resident Evil series, and was first announced in 2005â??the same year its predecessor Resident Evil 4 was released. Resident Evil 5 was released for the PlayStation 3 and Xbox 360 consoles in March 2009 and for Microsoft Windows in September of that year. The game''s plot involves an investigation by Bioterrorism Security Assessment Alliance (BSAA) agents Chris Redfield and Sheva Alomar of a terrorist threat in Kijuju, a fictional region of Africa. Chris soon learns that he must confront his past in the form of an old enemy, Albert Wesker, and his former partner, Jill Valentine.\r\n\r\nThe gameplay of Resident Evil 5 was similar to that of the previous installment, though it is the first game in series designed for two-player co-operative gameplay. It has also been considered to be the first game in the main series to depart from the survival horror genre, with critics saying it had more resemblance to an action game. Motion capture was used for the game''s cutscenes, and it was the first video game to use a virtual camera system. Producer Jun Takeuchi said that three years of actual development time were spent on the game, after a year devoted to concept and planning. At its development peak, about 110 people worked on the project. Several staff members from the original Resident Evil worked on Resident Evil 5.\r\n\r\nResident Evil 5 had a mostly positive reception, although it was criticized for problems with its controls. The game also received some initial complaints of racism, though an investigation by the British Board of Film Classification found such complaints were unsubstantiated. Resident Evil 5 was re-released for PlayStation 4 and Xbox One in June 2016. As of September 2016 it has sold over 7.1 million units, making it the franchise''s best-selling individual game and also the best-selling Capcom game of all-time. A sequel, Resident Evil 6, was released in 2012.', 'Resident Evil 5.jpg'),
(5, 'Rambo', 'rambo online', 'Rambo.png'),
(6, 'Dora The Explorer', 'This is dora. Apakah kamu melihat saya?', 'dora.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(8, 'auth', 'group'),
(7, 'auth', 'permission'),
(9, 'auth', 'user'),
(6, 'contenttypes', 'contenttype'),
(1, 'steam', 'discussion'),
(2, 'steam', 'friends'),
(4, 'steam', 'post'),
(5, 'steam', 'thread'),
(3, 'steam', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'steam', '0001_initial', '2017-04-01 13:07:00.583942'),
(2, 'contenttypes', '0001_initial', '2017-04-02 10:45:08.119851'),
(3, 'contenttypes', '0002_remove_content_type_name', '2017-04-02 10:45:09.607654'),
(4, 'auth', '0001_initial', '2017-04-02 11:00:09.909178'),
(5, 'auth', '0002_alter_permission_name_max_length', '2017-04-02 11:00:10.862541'),
(6, 'auth', '0003_alter_user_email_max_length', '2017-04-02 11:00:11.687528'),
(7, 'auth', '0004_alter_user_username_opts', '2017-04-02 11:00:11.764975'),
(8, 'auth', '0005_alter_user_last_login_null', '2017-04-02 11:00:12.499504'),
(9, 'auth', '0006_require_contenttypes_0002', '2017-04-02 11:00:12.518537'),
(10, 'auth', '0007_alter_validators_add_error_messages', '2017-04-02 11:00:12.563577'),
(11, 'auth', '0008_alter_user_username_max_length', '2017-04-02 11:00:13.347893'),
(12, 'steam', '0002_auto_20170407_2234', '2017-04-07 15:35:26.758983'),
(13, 'steam', '0003_auto_20170414_1658', '2017-04-14 10:15:52.448824');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `fid` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `friendID` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`fid`, `userID`, `friendID`, `status`) VALUES
(1, 2, 6, 1),
(2, 4, 7, 1),
(3, 6, 2, 1),
(4, 7, 4, 1),
(5, 2, 3, 1),
(6, 3, 2, 1),
(7, 2, 1, 1),
(8, 2, 4, 0),
(9, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `postID` int(11) NOT NULL,
  `threadID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `message` text NOT NULL,
  `postDateTime` datetime NOT NULL,
  `updateDateTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postID`, `threadID`, `userID`, `message`, `postDateTime`, `updateDateTime`) VALUES
(1, 1, 2, 'Please create your thread with english and do not spam others!', '2017-03-10 07:55:59', '2017-03-10 07:55:59'),
(2, 2, 6, 'bug fixed 7.01', '2017-03-10 08:09:58', '2017-03-10 08:09:58'),
(3, 1, 6, 'This post has been deleted!', '2017-03-10 08:13:04', '2017-03-10 08:13:04'),
(4, 3, 4, 'jadi bagus', '2017-03-10 09:48:11', '2017-03-10 09:48:11'),
(5, 2, 4, 'ah, bohong ini penipuan =D\r\nunfriend', '2017-03-10 09:49:03', '2017-03-10 09:49:03'),
(6, 4, 2, 'dilarang merokok', '2017-03-10 09:53:22', '2017-03-10 09:53:22'),
(7, 5, 7, 'hai', '2017-03-10 09:55:54', '2017-03-10 09:55:54'),
(8, 2, 6, 'halo ini test postman, maaf diganti ulang', '2017-03-10 09:48:11', '2017-03-10 09:48:11');

-- --------------------------------------------------------

--
-- Table structure for table `thread`
--

CREATE TABLE `thread` (
  `threadID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `discussionID` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `publishDateTime` datetime NOT NULL,
  `isPinned` tinyint(4) NOT NULL,
  `categoryType` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`threadID`, `userID`, `discussionID`, `title`, `publishDateTime`, `isPinned`, `categoryType`) VALUES
(1, 2, 1, 'PINNED: Rules', '2017-03-10 07:55:58', 1, 1),
(2, 6, 1, 'how to play dota 2', '2017-03-10 08:09:58', 0, 1),
(3, 4, 1, 'game crash', '2017-03-10 09:48:11', 0, 1),
(4, 2, 1, 'PINNED: Rules baru nomor 2', '2017-03-10 09:53:22', 1, 1),
(5, 7, 5, 'PINNED: ini rules', '2017-03-10 09:55:54', 1, 1),
(6, 2, 1, 'PINNED: Rules', '2017-03-10 07:55:58', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `imageURL` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `joinDate` datetime NOT NULL,
  `discussionID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `username`, `password`, `email`, `imageURL`, `description`, `name`, `country`, `province`, `city`, `joinDate`, `discussionID`) VALUES
(1, 'admin', 'admin', 'admin@steam.com', '1.png', '', '', '', '', '', '2017-03-01 00:00:00', NULL),
(2, 'gaunled', 'gaunled', 'gaunled@gmail.com', '2.png', '1115001', 'Daniel Gaunled', 'Indonesia', 'Jawa Barat', 'Bandung', '2017-03-10 07:37:01', 1),
(3, 'sujana', 'sujana', 'sujana@gmail.com', '', '', '', '', '', '', '2017-03-10 07:37:15', 2),
(4, 'kenny123', 'kenny', 'kenny@gmail.com', '4.jpg', 'saya adalah kenny', 'Kenny reynaldo', 'Indonesia', 'Jawa Barat', 'Bandung BINGTIS', '2017-03-10 07:37:29', 3),
(5, 'daniel', 'daniel', 'daniel@gmail.com', '', '', '', '', '', '', '2017-03-10 07:37:44', 4),
(6, 'audric', 'audric', 'audric@gmail.com', '', '', 'Audric Varian', '', '', '', '2017-03-10 07:38:10', NULL),
(7, 'richard', 'richard', 'richard@gmail.com', '7.png', 'Ini richard :D', 'Richard Dwiputra', 'Indonesia', 'Jawabarat', 'Bandung', '2017-03-10 09:43:57', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissi_permission_id_84c5c92e_fk_auth_permission_id` (`permission_id`);

--
-- Indexes for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indexes for table `auth_user`
--
ALTER TABLE `auth_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_groups_user_id_94350c0c_uniq` (`user_id`,`group_id`),
  ADD KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`);

--
-- Indexes for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_user_permissions_user_id_14a6b632_uniq` (`user_id`,`permission_id`),
  ADD KEY `auth_user_user_perm_permission_id_1fbb5f2c_fk_auth_permission_id` (`permission_id`);

--
-- Indexes for table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`discussionID`);

--
-- Indexes for table `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indexes for table `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`fid`),
  ADD KEY `friendID_friends` (`friendID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`postID`),
  ADD KEY `userID_post` (`userID`),
  ADD KEY `threadID_post` (`threadID`);

--
-- Indexes for table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`threadID`),
  ADD KEY `userID_thread` (`userID`),
  ADD KEY `discussionID_thread` (`discussionID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `discussionID_user` (`discussionID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `auth_user`
--
ALTER TABLE `auth_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `discussionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `friends`
--
ALTER TABLE `friends`
  MODIFY `fid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `postID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `thread`
--
ALTER TABLE `thread`
  MODIFY `threadID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissi_permission_id_84c5c92e_fk_auth_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Constraints for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permissi_content_type_id_2f476e4b_fk_django_content_type_id` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Constraints for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD CONSTRAINT `auth_user_user_perm_permission_id_1fbb5f2c_fk_auth_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friendID_friends` FOREIGN KEY (`friendID`) REFERENCES `user` (`userID`),
  ADD CONSTRAINT `userID_friends` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `threadID_post` FOREIGN KEY (`threadID`) REFERENCES `thread` (`threadID`),
  ADD CONSTRAINT `userID_post` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `discussionID_thread` FOREIGN KEY (`discussionID`) REFERENCES `discussion` (`discussionID`),
  ADD CONSTRAINT `userID_thread` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `discussionID_user` FOREIGN KEY (`discussionID`) REFERENCES `discussion` (`discussionID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
