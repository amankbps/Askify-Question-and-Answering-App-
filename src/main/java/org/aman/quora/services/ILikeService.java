package org.aman.quora.services;

import org.aman.quora.dto.LikeRequestDTO;
import org.aman.quora.dto.LikeResponseDTO;
import reactor.core.publisher.Mono;

public interface ILikeService {

    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, String targetType);
    Mono<LikeResponseDTO> countDisLikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<LikeResponseDTO>toggleLike(LikeRequestDTO likeRequestDTO);

}
