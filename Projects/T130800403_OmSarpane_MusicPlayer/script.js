// DOM Elements
const song = document.getElementById("song");
const progress = document.getElementById("progress");
const playPause = document.getElementById("play-pause");
const actionIcon = document.getElementById("action");

const title = document.querySelector("h1");
const artist = document.querySelector("p");
const img = document.querySelector(".song-img");

const prev = document.querySelector(".controls div:nth-child(1)");
const next = document.querySelector(".controls div:nth-child(3)");

const currentTimeEl = document.getElementById("current-time");
const durationEl = document.getElementById("duration");
const volumeSlider = document.getElementById("volume");

const searchInput = document.getElementById("search");
const heartBtn = document.querySelector("nav .circle i.fa-heart");
const toggleFavoritesBtn = document.getElementById("toggle-favorites");

let songList = [];
let filteredList = [];
let currentSong = 0;
let favorites = JSON.parse(localStorage.getItem("favorites")) || [];
let showOnlyFavorites = false;

function formatTime(seconds) {
	const min = Math.floor(seconds / 60);
	const sec = Math.floor(seconds % 60);
	return `${min}:${sec < 10 ? "0" + sec : sec}`;
}

function loadSong(index) {
	if (!filteredList.length) return;
	const songData = filteredList[index];
	const songId = songData.id;

	song.src = `assets/audio/${songId}.mp3`;
	img.src = `assets/images/${songId}.jpg`;
	title.textContent = songData.title;
	artist.textContent = songData.artist;

	document.querySelector(".container").style.setProperty(
		"--bg-image",
		`url(assets/images/${songId}.jpg)`
	);

	if (favorites.includes(songData.id)) {
		heartBtn.classList.remove("fa-regular");
		heartBtn.classList.add("fa-solid");
	} else {
		heartBtn.classList.remove("fa-solid");
		heartBtn.classList.add("fa-regular");
	}

	progress.value = 0;
	currentTimeEl.textContent = "0:00";
	durationEl.textContent = "0:00";
	actionIcon.classList.remove("fa-pause");
	actionIcon.classList.add("fa-play");

	currentSong = index;
}

playPause.addEventListener("click", () => {
	if (song.paused) {
		song.play();
		actionIcon.classList.remove("fa-play");
		actionIcon.classList.add("fa-pause");
	} else {
		song.pause();
		actionIcon.classList.remove("fa-pause");
		actionIcon.classList.add("fa-play");
	}
});

song.addEventListener("loadedmetadata", () => {
	progress.max = song.duration;
	durationEl.textContent = formatTime(song.duration);
	currentTimeEl.textContent = formatTime(song.currentTime);
});

song.addEventListener("timeupdate", () => {
	progress.value = song.currentTime;
	currentTimeEl.textContent = formatTime(song.currentTime);
});

progress.addEventListener("input", () => {
	song.currentTime = progress.value;
});

volumeSlider.addEventListener("input", () => {
	song.volume = volumeSlider.value;
});

next.addEventListener("click", () => {
	currentSong = (currentSong + 1) % filteredList.length;
	loadSong(currentSong);
	song.play();
	actionIcon.classList.remove("fa-play");
	actionIcon.classList.add("fa-pause");
});

prev.addEventListener("click", () => {
	currentSong = (currentSong - 1 + filteredList.length) % filteredList.length;
	loadSong(currentSong);
	song.play();
	actionIcon.classList.remove("fa-play");
	actionIcon.classList.add("fa-pause");
});

song.addEventListener("ended", () => {
	currentSong = (currentSong + 1) % filteredList.length;
	loadSong(currentSong);
	song.play();
});

searchInput.addEventListener("input", () => {
	const query = searchInput.value.toLowerCase();
	const baseList = showOnlyFavorites
		? songList.filter((song) => favorites.includes(song.id))
		: songList;

	filteredList = baseList.filter(
		(song) =>
			song.title.toLowerCase().includes(query) ||
			song.artist.toLowerCase().includes(query)
	);

	if (filteredList.length) {
		loadSong(0);
	} else {
		title.textContent = "No results found";
		artist.textContent = "";
		img.src = "";
		song.pause();
		actionIcon.classList.remove("fa-pause");
		actionIcon.classList.add("fa-play");
	}
});

heartBtn.parentElement.addEventListener("click", () => {
	const currentId = filteredList[currentSong].id;
	if (favorites.includes(currentId)) {
		favorites = favorites.filter((id) => id !== currentId);
	} else {
		favorites.push(currentId);
	}
	localStorage.setItem("favorites", JSON.stringify(favorites));
	loadSong(currentSong);
	song.play();  // Ensure song continues playing
});

toggleFavoritesBtn.addEventListener("click", () => {
	showOnlyFavorites = !showOnlyFavorites;
	if (showOnlyFavorites) {
		toggleFavoritesBtn.classList.add("active");
		filteredList = songList.filter((song) => favorites.includes(song.id));
	} else {
		toggleFavoritesBtn.classList.remove("active");
		filteredList = [...songList];
	}
	if (filteredList.length > 0) {
		loadSong(0);
		song.play();  // Ensure song continues playing
	} else {
		title.textContent = "No favorite songs";
		artist.textContent = "";
		img.src = "";
		song.pause();
	}
});

// Load song data
fetch("songs.json")
	.then((res) => res.json())
	.then((data) => {
		songList = data;
		filteredList = [...songList];
		loadSong(0);
	});
